/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example

import scala.concurrent.{ExecutionContext, Future}
import akka.actor.{ActorSystem, Scheduler}
import akka.stream.{ActorMaterializer, Materializer}
import com.google.inject.{AbstractModule, Guice, Injector, PrivateModule}
import com.typesafe.config.{Config, ConfigFactory}
import org.lunatech.example.controllers.MainController
import net.codingwell.scalaguice.{ScalaModule, ScalaPrivateModule}
import org.slf4j.{Logger, LoggerFactory}

class GuiceModule(val config: Config) extends AbstractModule with ScalaModule {
  private val log: Logger = LoggerFactory.getLogger(getClass)

  override def configure(): Unit = {
    val system = ActorSystem("server-system")
    import system.dispatcher

    bind[Config].toInstance(config)
    bind[ActorSystem].annotatedWithName("server-system").toInstance(system)
    bind[ActorSystem].toInstance(ActorSystem())
    bind[ActorMaterializer].toInstance(ActorMaterializer()(system))
    bind[ExecutionContext].toInstance(system.dispatcher)
    bind[Scheduler].toInstance(system.scheduler)
    bind[Materializer].toInstance(ActorMaterializer()(system))

    bind[MainController].asEagerSingleton()
  }
}

class PrivateGuiceModule extends PrivateModule with ScalaPrivateModule {
  def configure(): Unit = {}
}

object GuiceInjector {
  lazy val create: Injector =
    Guice.createInjector(
      new GuiceModule(ConfigFactory.load()),
      new PrivateGuiceModule()
    )
}
