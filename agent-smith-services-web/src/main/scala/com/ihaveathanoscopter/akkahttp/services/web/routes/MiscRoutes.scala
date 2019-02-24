package com.ihaveathanoscopter.akkahttp.services.web.routes

import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.Materializer
import com.ihaveathanoscopter.akkahttp.services.web.AboutApp
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.{Tag, Tags}
import javax.ws.rs.{Consumes, GET, Path}
import org.json4s.{DefaultFormats, jackson}

import scala.concurrent.ExecutionContext

/**
  * Miscellaneous routes
  *
  * @author Dexter Legaspi
  */

@Path("/")
class MiscRoutes(implicit ec: ExecutionContext, mat: Materializer) extends Json4sSupport with Directives {

  @Path("/rest/v1/about")
  @GET
  @Consumes(Array("application/json"))
  @Tags(Array(new Tag(name = "Miscellaneous", description = "Miscellaneous routes")))
  @Operation(summary = "Return application information",
    description = "Return application information",
    responses = Array(new ApiResponse(responseCode = "200",
      description = "Return information about this REST application",
      content = Array(new Content(schema = new Schema(implementation = classOf[AboutApp]))))))
  def aboutAppGetRoute = path("about") {
    implicit val serialization = jackson.Serialization // or native.Serialization
    implicit val formats = DefaultFormats

    get {
      complete {
        AboutApp("agent-smith")
      }
    }
  }

  val routes: Route = aboutAppGetRoute
}
