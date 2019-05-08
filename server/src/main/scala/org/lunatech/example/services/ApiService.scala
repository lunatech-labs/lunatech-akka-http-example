/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example.services

import akka.actor.ActorSystem
import akka.http.scaladsl.server
import akka.stream.Materializer
import org.lunatech.example.controllers.MainController
import javax.inject.Inject

import scala.concurrent.ExecutionContext

class ApiService @Inject()(
    controller: MainController
)(implicit executor: ExecutionContext, as: ActorSystem, mat: Materializer) {

  def routes: server.Route =
    controller.routes
}
