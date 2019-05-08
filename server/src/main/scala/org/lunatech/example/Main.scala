/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example

import scala.util.{Failure, Success}
import akka.actor.ActorSystem
import net.codingwell.scalaguice.InjectorExtensions._
import org.slf4j.{Logger, LoggerFactory}
import org.lunatech.example.services.ConfigService
import kamon.Kamon
import kamon.datadog.DatadogAgentReporter

import scala.concurrent.Future

object Main extends ConfigService {
  private val log: Logger = LoggerFactory.getLogger(getClass)
  Kamon.addReporter(new DatadogAgentReporter())

  def main(args: Array[String]) {

    val system = ActorSystem("main-system")
    implicit val ec = system.dispatcher

    val injector = GuiceInjector.create

    injector
      .instance[WebServer]
      .bind()
      .onComplete {
        case Success(binding) =>
          log.info("Success! Bound on {}", binding.localAddress)
        case Failure(error) => log.error("Failed", error)
        case _              => system.terminate()
      }
  }
}
