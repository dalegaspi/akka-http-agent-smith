package com.ihaveathanoscopter.akkahttp.services.web

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.ExecutionContext

/**
  * Microservices entry point
  *
  * @author Dexter Legaspi
  */
object ServiceMain extends App with RestServices {
  //Kamon.start()

  implicit val actorSystem = ActorSystem("agent-smith")
  implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = actorSystem.dispatcher

  implicit val config: Config = ConfigFactory.load()
  val host = config.getString("http.host")

  val bindingFuture = Http().bindAndHandle(routes, host, port)

  sys.addShutdownHook(actorSystem.terminate())
  //sys.addShutdownHook(Kamon.shutdown())
}
