/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext

trait WebApi {

  // Entry point for creating and looking up actors.
  implicit val system: ActorSystem

  // Materializes the list of requests
  implicit val materializer: ActorMaterializer

  // Futures need a context to be executed on.
  implicit val executor: ExecutionContext

}
