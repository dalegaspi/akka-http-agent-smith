package com.ihaveathanoscopter.akkahttp.services.web.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.server._
import akka.stream.Materializer
import com.softwaremill.macwire._
import com.typesafe.config.Config

import scala.concurrent.ExecutionContext

/**
  * @author Dexter Legaspi
  * @param ec ExecutionContext
  * @param mat Materializer
  * @param actorSystem AcheronSystem
  * @param config the Typesafe [[Config]] object
  */
class BaseRoutes(implicit actorSystem: ActorSystem,
                 ec: ExecutionContext,
                 mat: Materializer,
                 config: Config) extends Directives with RouteConcatenation {
  val routes: Route = pathPrefix("rest" / "v1") {
    (wire[MiscRoutes]).routes
  }
}
