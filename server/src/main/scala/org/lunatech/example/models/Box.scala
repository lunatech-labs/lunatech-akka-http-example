/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example.models

import io.circe.{Decoder, Encoder}

case class Box(x: Int, y: Int, width: Int, height: Int)

/* Circe json serialization case classes */
object Box {
  import _root_.io.circe.generic.semiauto._

  implicit lazy val encoder: Encoder[Box] = deriveEncoder[Box]
  implicit lazy val decoder: Decoder[Box] = deriveDecoder[Box]
}
