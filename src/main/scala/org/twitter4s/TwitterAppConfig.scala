package org.twitter4s

import com.typesafe.config._

/**
 * Created by patrick on 21/01/15.
 * This class represent a twitter application configuration.
 */
case class TwitterAppConfig (
  consumerKey: String,
  consumerSecret: String,
  accessToken: String,
  accessTokenSecret: String
)

object TwitterAppConfig {

  def fromConfig(config:Config): TwitterAppConfig =
    TwitterAppConfig(
      config.getString("twitter4s.appConfig.oauth.consumerKey"),
      config.getString("twitter4s.appConfig.oauth.consumerSecret"),
      config.getString("twitter4s.appConfig.oauth.accessToken"),
      config.getString("twitter4s.appConfig.oauth.accessTokenSecret")
    )

  def fromDefaultConfig(): TwitterAppConfig = fromConfig(ConfigFactory.load())

}