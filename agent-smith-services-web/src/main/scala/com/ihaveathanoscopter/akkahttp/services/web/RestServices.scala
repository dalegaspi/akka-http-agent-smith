package com.ihaveathanoscopter.akkahttp.services.web

import akka.actor.ActorSystem
import akka.http.scaladsl.server.RouteConcatenation
import akka.stream.ActorMaterializer
import com.ihaveathanoscopter.akkahttp.services.web.routes.BaseRoutes
import com.softwaremill.macwire._
import com.softwaremill.tagging._
import com.typesafe.config.Config

import scala.concurrent.ExecutionContext

/**
  * @author Dexter Legaspi
  */
trait RestServices extends RouteConcatenation with CorsSupport {
  implicit val actorSystem: ActorSystem
  implicit val actorMaterializer: ActorMaterializer
  implicit val ec: ExecutionContext

  implicit val config: Config

  lazy val version = config.getString("http.version").taggedWith[SwaggerVersion]
  lazy val swaggerHost = config.getString("http.swagger.host").taggedWith[SwaggerHostAddress]
  lazy val port = config.getInt("http.port")

  lazy val swaggerDocService : SwaggerDocService = wire[SwaggerDocService]
  lazy val baseRoutes = wire[BaseRoutes]

  lazy val routes = corsHandler(baseRoutes.routes) ~
    swaggerDocService.site ~
    swaggerDocService.routes ~
    corsHandler(swaggerDocService.routes)
}
