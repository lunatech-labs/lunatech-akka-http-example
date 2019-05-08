/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example.util

case class Paginate private (page: Int, size: Int) {
  def start: Int = (page - 1) * size

  def end: Int = (page - 1) * size + size
}

object Paginate {
  def apply(page: Int = 1, size: Int = 10): Either[Exception, Paginate] = // scalastyle:ignore
    if (page <= 0) {
      Left(
        new IllegalArgumentException("The page number must be higher than 0"))
    } else if (size <= 0) {
      Left(
        new IllegalArgumentException("The size number must be higher than 0"))
    } else {
      Right(new Paginate(page, size))
    }
}
