package com.ihaveathanoscopter.akkahttp.services.web

import java.net.InetAddress

import com.ihaveathanoscopter.akkahttp.services.web.BuildInfo

/**
  * @author Dexter Legaspi
  */

/**
  * About information
  *
  * @param title Title
  * @param version Version String
  */
case class AboutApp(title: String,
                    version: String = BuildInfo.version,
                    buildDateTime: String = BuildInfo.builtAtString,
                    server: String = InetAddress.getLocalHost.getHostAddress
                   )
