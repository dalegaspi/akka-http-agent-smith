
logLevel := sbt.Level.Warn

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.9")
//addSbtPlugin("com.typesafe.sbt" % "sbt-aspectj" % "0.10.6")
addSbtPlugin("se.marcuslonnberg" % "sbt-docker" % "1.5.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.4.0")
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.7.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")
//addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.3")
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.1.0-M11")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
