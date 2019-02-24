package com.ihaveathanoscopter.akkahttp.services.utils

import scala.concurrent.duration.Duration

/**
  * @author Dexter Legaspi
  * @since 8/24/17
  */
case class ServiceConfiguration(timeouts: ServiceTimeoutConfiguration,
                                mountain: MicroserviceConfiguration,
                                aquarius: MicroserviceConfiguration,
                                cygnus: MicroserviceConfiguration,
                                gozer: MicroserviceConfiguration)

case class ServiceTimeoutConfiguration(httpPostTimeout: Duration, httpGetTimeout: Duration)

case class MicroserviceConfiguration(baseUrl: String)
