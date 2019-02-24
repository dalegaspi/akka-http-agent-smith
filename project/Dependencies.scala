import sbt._

object Dependencies {
  val javaxWsRs = "javax.ws.rs" % "javax.ws.rs-api" % "2.0.1"
  val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test

  val scalaTestVersion = "3.0.4"
  val scalaTestAndMock = Seq(
    "org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    scalaMock
  )

  val jacksonVersion = "2.9.8"
  val jacksonScalaVersion = "2.9.8"
  val jackson = Seq(
    "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
    "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
    "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % jacksonVersion,
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % jacksonVersion,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % jacksonVersion,
    "com.fasterxml.jackson.jaxrs" % "jackson-jaxrs-base" % jacksonVersion,
    "com.fasterxml.jackson.jaxrs" % "jackson-jaxrs-json-provider" % jacksonVersion,
    "com.fasterxml.jackson.module" % "jackson-module-jaxb-annotations" % jacksonVersion,
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonScalaVersion,
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-cbor" % jacksonScalaVersion
  )

  val sCoverage = "org.scoverage" %% "scalac-scoverage-runtime" % "1.3.0"
  lazy val akkaVersion = "2.5.21"
  lazy val akkaHttpVersion = "10.1.7"
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaHttpCore = "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion

  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  val slf4jNop = "org.slf4j" % "slf4j-nop" % "1.7.25"
  val swaggerAkka = "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.0.1"
  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
  val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.1.7"
  val typesafeConfig = "com.typesafe" % "config" % "1.3.1"

  lazy val macWireVersion = "2.3.0"
  val macWireMacros = "com.softwaremill.macwire" %% "macros" % macWireVersion % "provided"
  val macWireUtil = "com.softwaremill.macwire" %% "util" % macWireVersion
  val macWireProxy = "com.softwaremill.macwire" %% "proxy" % macWireVersion

  lazy val akkaHttpJsonVersion = "1.25.2"

  lazy val circeVersion = "0.8.0"
  val circeCore = "io.circe" %% "circe-core" % circeVersion
  val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
  val circeParser = "io.circe" %% "circe-parser" % circeVersion
  val akkaHttpJsonCirce = "de.heikoseeberger" %% "akka-http-circe" % akkaHttpJsonVersion

  lazy val json4sVersion = "3.6.5"
  val json4sNative = "org.json4s" %% "json4s-native" % json4sVersion
  val json4sJackson = "org.json4s" %% "json4s-jackson" % json4sVersion
  val json4sExt = "org.json4s" %% "json4s-ext" % json4sVersion
  val akkaHttpJson4s = "de.heikoseeberger" %% "akka-http-json4s" % akkaHttpJsonVersion

  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.1.1"
  val scalaParser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.1"

  lazy val dispatchCoreVersion = "0.11.3"
  val dispatchCore = "net.databinder.dispatch" %% "dispatch-core" % dispatchCoreVersion

  val kafkaClient = "org.apache.kafka" % "kafka-clients" % "0.8.2.2"

  val kamonVersion = "0.6.6"
  val kamonCore = "io.kamon" %% "kamon-core" % kamonVersion
  val kamonStatsD = "io.kamon" %% "kamon-statsd" % kamonVersion
  val kamonAkka = "io.kamon" %% "kamon-akka-2.5" % "0.6.7"
  val kamonLogReporter = "io.kamon" %% "kamon-log-reporter" % kamonVersion
  val kamonSystemMetrics = "io.kamon" %% "kamon-system-metrics" % kamonVersion
  val aspectJWeaver = "org.aspectj" % "aspectjweaver" % "1.8.10"

  // kamon plugins from Tradeshift
  val kamonTsAkka = Seq(
    ("com.tradeshift" %% "ts-reaktive-kamon-akka" % "0.0.24").exclude("org.slf4j", "slf4j-log4j12"),
    ("com.tradeshift" %% "ts-reaktive-kamon-akka-http" % "0.0.24").exclude("org.slf4j", "slf4j-log4j12"),
    ("com.readytalk" % "metrics3-statsd" % "4.2.0").exclude("org.slf4j", "slf4j-log4j12")
  )

  // DynamoDB client
  val awsDynamoDb = "jp.co.bizreach" %% "aws-dynamodb-scala" % "0.0.5"

  // AWS Scala
  val awsScala = "com.github.seratch" %% "awscala" % "0.6.+"

  // QuickLens
  val quickLens = "com.softwaremill.quicklens" %% "quicklens" % "1.4.8"

  // ScalaJ HTTP
  val scalajHttp = "org.scalaj" %% "scalaj-http" % "2.3.0"

  // ncala-time
  val nScalaTime = "com.github.nscala-time" %% "nscala-time" % "2.16.0"

  // scalacache
  val scalaCacheVersion = "0.9.4"
  val scalaCacheRedis = "com.github.cb372" %% "scalacache-redis" % scalaCacheVersion
  val scalaCacheMemcached = "com.github.cb372" %% "scalacache-memcached" % scalaCacheVersion
  val scalaCacheCaffeine = "com.github.cb372" %% "scalacache-caffeine" % scalaCacheVersion
  val scalaMemcached = "io.monix" %% "shade" % "1.9.5"

  // Apache Commons Lang
  val apacheCommonsLang = "org.apache.commons" % "commons-lang3" % "3.6"

  // slick
  val typesafeSlick = "com.typesafe.slick" %% "slick" % "3.2.0"
  val postgreSql = "org.postgresql" % "postgresql" % "42.1.1"
  val hikariCP = "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0"

  // jsonpath
  val gatlingJsonPath = "io.gatling" %% "jsonpath" % "0.6.10"
  val awsKinesis = "com.amazonaws" % "amazon-kinesis-client" % "1.7.5"
  val bizreachAwsKinesis = ("jp.co.bizreach" %% "aws-kinesis-scala" % "0.0.6").exclude("com.amazonaws", "aws-java-sdk-kinesis")

  // json transformation
  val joltCore = "com.bazaarvoice.jolt" % "jolt-core" % "0.1.0"
  val joltJsonUtils = "com.bazaarvoice.jolt" % "json-utils" % "0.1.0"

  // scala extension to typesafe config
  val scalaConfig = "com.github.andr83" %% "scalaconfig" % "0.3"

  // gigahorse http client
  val gigahorseHttpClient = "com.eed3si9n" %% "gigahorse-asynchttpclient" % "0.3.0"
}
