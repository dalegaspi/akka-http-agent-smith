package com.ihaveathanoscopter.akkahttp.services.web

import akka.http.scaladsl.model.StatusCodes
import com.github.swagger.akka._
import com.github.swagger.akka.model.Info
import com.ihaveathanoscopter.akkahttp.services.web.routes.MiscRoutes
import com.softwaremill.tagging._

trait SwaggerVersion
trait SwaggerHostAddress


/**
  *
  * aped from: https://github.com/cdiniz/slick-akka-http/blob/master/src/main/scala/utils/SwaggerDocService.scala
  *
  * @author Dexter Legaspi
  */
class SwaggerDocService(address: String @@ SwaggerHostAddress,
                        port: Int,
                        versionInfo: String @@ SwaggerVersion) extends SwaggerHttpService {
  override val apiClasses: Set[Class[_]] = Set(classOf[MiscRoutes])

  override val host = address + ":" + port
  override val info = Info(version = versionInfo)
  override val unwantedDefinitions = Seq("Function1", "Function1RequestContextFutureRouteResult")
  override val apiDocsPath = "api-docs" //where you want the swagger-json endpoint exposed

  def site = pathPrefix("swagger") {
    getFromResourceDirectory("swagger") ~
      pathSingleSlash {
        get {
          redirect("index.html", StatusCodes.PermanentRedirect)
        }
      }
  }
}