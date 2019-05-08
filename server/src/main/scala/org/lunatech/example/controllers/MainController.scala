/*
 * Copyright Lunatech 2019
 */

// @formatter:off
package org.lunatech.example.controllers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import org.lunatech.example.models.Box
import org.lunatech.example.services.ConfigService
import javax.inject.{Inject, Singleton}
import org.slf4j.{Logger, LoggerFactory}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._

import scala.concurrent.ExecutionContext

@Singleton
// scalastyle:off multiple.string.literals
class MainController @Inject()()(implicit val ec: ExecutionContext) extends ConfigService {

  private val log: Logger = LoggerFactory.getLogger(getClass)


  // Order of routes matters for the moment
  val routes: Route =
    start ~
      healthcheck

  def start: server.Route = path("test") {
    get {
      val box = Box(1, 1, 1, 1)
      complete(StatusCodes.OK -> box)
    }
  }

  def healthcheck: server.Route = path("healthcheck") {
    get {
      complete(StatusCodes.OK)
    }
  }
}
