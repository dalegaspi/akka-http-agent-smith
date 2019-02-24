import sbt._

object Resolvers {
  val cakeSolutionsMaven = Resolver.bintrayRepo("cakesolutions", "maven")
  val tradeshiftMaven = Resolver.bintrayRepo("jypma", "maven")
  val readyTalk = Resolver.bintrayRepo("readytalk", "maven")
  val superSafe = "Artima Maven Repository" at "http://repo.artima.com/releases"
}