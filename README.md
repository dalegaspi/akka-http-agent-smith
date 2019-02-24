# akka-http-agent-smith
A Basic Akka-HTTP Microservice With JSON Support

![java-img](https://img.shields.io/badge/java-8-orange.svg?style=flat-square) ![java-img](https://img.shields.io/badge/scala-2.12-orange.svg?style=flat-square) ![java-img](https://img.shields.io/badge/sbt-1.2-orange.svg?style=flat-square) ![java-img](https://img.shields.io/badge/akka-2.5-orange.svg?style=flat-square) ![java-img](https://img.shields.io/badge/swagger-3-orange.svg?style=flat-square) 

## Overview

This is a basic HTTP Microservice that natively supports JSON as payload.  It is a combination of existing awesome stuff already available:

- [Akka HTTP](https://github.com/akka/akka-http)
- [json4s](https://github.com/json4s/json4s)
- [akka-http-json](https://github.com/hseeberger/akka-http-json)
- [sbt-assembly](https://github.com/sbt/sbt-assembly)
- [sbt-docker](https://github.com/marcuslonnberg/sbt-docker)
- [swagger-akka-http](https://github.com/swagger-akka-http/swagger-akka-http)
- [ScalaTest](http://www.scalatest.org/)
- [scalaconfig](https://github.com/andr83/scalaconfig)
- [sbt-revolver](https://github.com/spray/sbt-revolver)
- [sbt-buildinfo](https://github.com/sbt/sbt-buildinfo)
- [sbt-git](https://github.com/sbt/sbt-git)
- [SLF4J](https://www.slf4j.org/)
- [Logback](https://logback.qos.ch/)

The goal of this project is to showcase Akka HTTP and be able to start with a basic microservice by just cloning this project.

## Features

- The project structure follows the best practice outlined in [sbt documentation](https://www.scala-sbt.org/1.x/docs/Multi-Project.html)
- This uses the vanilla Akka HTTP--i.e, nothing here prevents you from using anything that Akka HTTP framework allows you to do.
- **The code does not deal explicitly with JSON Marshallers/Unmarshallers.**  This is made possible by the awesome [akka-http-json](https://github.com/hseeberger/akka-http-json) library.  This elegantly adds JSON serialization/desarialization support by way of scala traits.  It also allows you to use the JSON library of your choice (this project users Jackson).  This allows you to just forget serialization/deserialization altogether and just concentrate on the core function of the web service by dealing with POJOs.  This also allows you to use the likes of [scalaxb](http://scalaxb.org) to generate case classes from XML schemas and use them in your project.
- Use [sbt-assembly](https://github.com/sbt/sbt-assembly) and/or [sbt-docker](https://github.com/marcuslonnberg/sbt-docker) to package your microservice
- Integrated Swagger support (with [swagger-akka-http](https://github.com/swagger-akka-http/swagger-akka-http))
- Logging has been setup to use SLF4J and Logback with **asynchronous logging**
- Use of scala classes with Typesafe config using [scalaconfig](https://github.com/andr83/scalaconfig)
- About page can be dynamically updated at compile/build time using [sbt-buildinfo](https://github.com/sbt/sbt-buildinfo).  Furthermore, You can also attach a git hash string to be used anywhere in the project using [sbt-git](https://github.com/sbt/sbt-git)

## Run/Build/Test

### Run Using SBT
    $ sbt agent-smith-services-web/run 
    
### Run Using SBT Revolver (Auto Reloading on File Change)
The `sbt-revolver` plugin enables easy restarting of the app during development.

To restart the app automatically on project code changes, run:
```
$ sbt ~agent-smith-services-web/reStart
```
    
### Run Using Docker (requires Docker installed on machine)

This runs the process in your local machine @ port 1138

    $ sbt docker
    $ docker run --rm --name neo -v $HOME/agent-smith_logs:/app/logs -p 1138:1138 com.ihaveathanoscopter.akkahttp/agent-smith-services:latest
    
## Building/Running Über JAR file

    $ sbt assembly
    $ java -jar agent-smith-services-web/target/scala-2.12/agent-smith-services-web.jar
    
## Quick Test

The Swagger should be available using this URL: [http://localhost:1138/swagger/index.html](http://localhost:1138/swagger/index.html)

The about JSON can be retrieved by:

    $ curl -X GET --header 'Accept: application/json' 'http://localhost:1138/rest/v1/about' 
    
This should return the following JSON:

```json
{
    "title": "agent-smith",
    "version": "1.0-SNAPSHOT",
    "buildDateTime": "2019-02-24 17:45:45.532",
    "server": "127.0.0.1"
}
```
   
  

