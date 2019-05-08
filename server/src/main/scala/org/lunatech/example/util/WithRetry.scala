/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example.util

import scala.concurrent.duration.{FiniteDuration, _}
import scala.concurrent.{ExecutionContext, Future}

import akka.actor.Scheduler
import akka.pattern.after

trait WithRetry {
  implicit def ec: ExecutionContext
  implicit def s: Scheduler

  private val RetryDelays: Seq[FiniteDuration] = Seq(
    10 milliseconds,
    20 milliseconds,
    50 milliseconds,
    100 milliseconds,
    100 milliseconds
  )

  protected def retry[T](f: () => Future[T],
                         delays: Seq[FiniteDuration] = RetryDelays): Future[T] =
    f() recoverWith {
      case _ if delays.nonEmpty => after(delays.head, s)(retry(f, delays.tail))
    }
}
