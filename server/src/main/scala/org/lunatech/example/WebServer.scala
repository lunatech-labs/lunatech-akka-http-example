/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.github.swagger.akka.SwaggerHttpService
import org.lunatech.example.controllers.MainController
import org.lunatech.example.services.{ApiService, ConfigService}
import io.swagger.models.Scheme
import javax.inject.{Inject, Singleton}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class WebServer @Inject()(apiService: ApiService)(
    implicit val system: ActorSystem,
    executor: ExecutionContext,
    materializer: ActorMaterializer)
    extends ConfigService {

  case class Swagger(apiClasses: Set[Class[_]]) extends SwaggerHttpService {

    override val host: String = serviceHost

    override def schemes: List[Scheme] = List(Scheme.forValue(serviceScheme))

    override val basePath: String = super.basePath + serviceUri

    override def routes: Route =
      super.routes ~ path("swagger") {
        getFromResource("swagger-ui/index.html")
      } ~ getFromResourceDirectory("swagger-ui")
  }

  def apiClasses: Set[Class[_]] =
    Set(
      classOf[MainController]
    )

  def bind(): Future[ServerBinding] =
    Http().bindAndHandle(
      apiService.routes,
      httpHost,
      httpPort
    )
}
