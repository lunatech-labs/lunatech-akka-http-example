package org.lunatech.example.controllers


import akka.actor.ActorSystem

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, MustMatchers}
import org.lunatech.example.WebApi
import org.lunatech.example.services._

import scala.concurrent.duration.DurationInt

class WorkerControllerSpec
    extends AsyncWordSpec
    with MustMatchers
    with BeforeAndAfterAll
    with ConfigService
    with WebApi
    with ScalatestRouteTest {

  override implicit val executor = system.dispatcher
  implicit def default(implicit system: ActorSystem) =
    RouteTestTimeout(new DurationInt(10).second)

  val workerController = new MainController()

  "A start endpoint" must {
    "accept a POST" in {
      Post("/start") ~> workerController.routes ~> check {
        status mustBe StatusCodes.OK
      }
    }
  }
}
