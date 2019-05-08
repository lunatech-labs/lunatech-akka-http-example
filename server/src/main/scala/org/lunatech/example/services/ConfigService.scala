/*
 * Copyright Lunatech 2019
 */

package org.lunatech.example.services

import com.typesafe.config.ConfigFactory

trait ConfigService {

  private val config = ConfigFactory.load()

  private val httpConfig = config.getConfig("http")

  val httpHost = httpConfig.getString("interface")

  val httpPort = httpConfig.getInt("port")

  val serviceHost = httpConfig.getString("serviceHost")

  val serviceScheme = httpConfig.getString("serviceScheme")

  val serviceUri = httpConfig.getString("serviceUri")

  private val apiKeysConfig = config.getConfig("apiKeys")

  val headerClientId = apiKeysConfig.getString("headerClientId")
}
