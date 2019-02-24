import Dependencies.sCoverage
import sbt.Keys._

// https://www.scala-sbt.org/1.x/docs/Multi-Project.html

import AssemblyMergeStrategies._
import Dependencies._
import Resolvers._

lazy val commonSettings = Seq(
  name := "akka-http-agent-smith", 
  git.formattedShaVersion := git.gitHeadCommit.value map { sha => sha.take(5) },
  organization := "com.ihaveathanoscopter.akkahttp",
  version := "1.0." + git.formattedShaVersion.value.getOrElse("00000"),
  scalaVersion := "2.12.8"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "akka-http-agent-smith",
    publishArtifact := false,
    publish := (),
    coverageEnabled in(Test, compile) := true,
    coverageEnabled in(Compile, compile) := false,
    resolvers ++= Seq(superSafe)
  ).
  settings(
    imageNames in docker := Seq(
      ImageName(s"${organization.value}/${name.value}-services:latest"), // set the docker file to acheron-services

      ImageName(
        namespace = Some(organization.value),
        repository = s"${name.value}-services",
        tag = Some("v" + version.value)
      )
    ),
    dockerfile in docker := {
      // The assembly task generates a fat JAR file
      val artifact: File = (assembly in `agent-smith-services-web`).value
      val artifactTargetPath = s"/app/${artifact.name}"

      new Dockerfile {
        from("amazoncorretto:8")
        add(artifact, artifactTargetPath)
        expose(1138)
        expose(22)
        volume("/app/logs")
        workDir("/app")
        env("LOG_DIR", "/app/logs")
        entryPoint("java",
          "-DLOGBACK_DIR=/app/logs",
          "-jar", artifactTargetPath)
      }
    }
  ).
  aggregate(`agent-smith-services-web`, `agent-smith-services-web-core`).
  enablePlugins(AssemblyPlugin, ScoverageSbtPlugin, DockerPlugin, GitVersioning)

lazy val `agent-smith-services-web` = (project in file("agent-smith-services-web")).
  settings(commonSettings: _*).
  settings(
    name := "agent-smith-services-web",
    version := "1.0-SNAPSHOT",
    // uncomment these lines when Kamon officially supports scala 2.12
    //
    //aspectjSettings,
    //AspectjKeys.verbose in Aspectj := true,
    //javaOptions <++= AspectjKeys.weaverOptions in Aspectj,
    //fork in run := true,
    libraryDependencies ++= Seq(
      javaxWsRs,
      sCoverage,
      akkaActor,
      akkaHttp,
      akkaHttpCore,
      akkaHttpSprayJson,
      akkaHttpTestKit,
      akkaSlf4j,
      logbackClassic,
      akkaStream,
      swaggerAkka,
      typesafeConfig,
      macWireMacros,
      macWireProxy,
      macWireUtil,
      //kamonCore,
      //kamonStatsD,
      //kamonAkka,
      //kamonLogReporter,
      //kamonSystemMetrics,
      akkaHttpJson4s,
      json4sExt,
      json4sJackson, // use jackson with json4s
      //json4sNative // use native with json4s
    ),
    libraryDependencies ++= jackson,
    // libraryDependencies ++= kamonTsAkka, -- scala 2.12 not yet supported
    libraryDependencies ++= scalaTestAndMock,
    coverageEnabled in(Test, compile) := true,
    coverageEnabled in(Compile, compile) := false,
    resolvers ++= Seq(cakeSolutionsMaven, superSafe, tradeshiftMaven),
    assemblyMergeStrategy in assembly := customMergeStrategy,
    mainClass in (Compile, run) := Some("com.ihaveathanoscopter.akkahttp.services.web.ServiceMain"),
    mainClass in reStart := Some("com.ihaveathanoscopter.akkahttp.services.web.ServiceMain"),
    mainClass in assembly := Some("com.ihaveathanoscopter.akkahttp.services.web.ServiceMain"),
    assemblyJarName in assembly := s"${name.value}.jar",
    publishArtifact := false,
    publish := ()
  ).
  dependsOn(`agent-smith-services-web-core`).
  enablePlugins(BuildInfoPlugin).
  settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "com.ihaveathanoscopter.akkahttp.services.web",
    buildInfoOptions += BuildInfoOption.BuildTime
  )

lazy val `agent-smith-services-web-core` = (project in file("agent-smith-services-web-core")).
  settings(commonSettings: _*).
  settings(
    name := "agent-smith-services-web-core",
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      sCoverage,
      typesafeConfig,
      scalaConfig
    ),
    libraryDependencies ++= scalaTestAndMock,
    coverageEnabled in(Test, compile) := true,
    coverageEnabled in(Compile, compile) := false,
    resolvers ++= Seq(cakeSolutionsMaven, superSafe),
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false, includeDependency = false),
    assemblyMergeStrategy in assembly := customMergeStrategy,
    publishArtifact := false,
    publish := ()
  )
