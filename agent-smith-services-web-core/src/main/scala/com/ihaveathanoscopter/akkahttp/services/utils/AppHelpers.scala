package com.ihaveathanoscopter.akkahttp.services.utils


import com.github.andr83.scalaconfig.{Reader, _}
import com.typesafe.config.Config

import scala.concurrent.duration.{Duration, FiniteDuration}


/**
  * @author Dexter Legaspi
  * @since 8/24/17
  */
object AppHelpers {

  /**
    * implicit class for services configuration
    *
    * @param conf
    */
  implicit class AppSettings(val conf: Config) {
    implicit def everestServicesConfigReader: Reader[ServiceConfiguration] = new Reader[ServiceConfiguration] {
      override def apply(config: Config, path: String): ServiceConfiguration = {
        val cbConfig = config.getConfig(path)

        ServiceConfiguration(
          timeouts = ServiceTimeoutConfiguration(
            httpGetTimeout = Duration(cbConfig.getString("timeouts.http.get")).asInstanceOf[FiniteDuration],
            httpPostTimeout = Duration(cbConfig.getString("timeouts.http.post")).asInstanceOf[FiniteDuration]),
          mountain = MicroserviceConfiguration(baseUrl = cbConfig.getString("mountain.base.url")),
          aquarius = MicroserviceConfiguration(baseUrl = cbConfig.getString("aquarius.base.url")),
          gozer = MicroserviceConfiguration(baseUrl = cbConfig.getString("gozer.base.url")),
          cygnus = MicroserviceConfiguration(baseUrl = cbConfig.getString("cygnus.base.url"))
        )
      }
    }

    /**
      * return a [[ServiceConfiguration]]
      * @return
      */
    def getServiceConfiguration(): ServiceConfiguration = {
      conf.as[ServiceConfiguration]("everest.services")
    }
  }
}
