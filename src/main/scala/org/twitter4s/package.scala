package org

import twitter4j.conf.{ConfigurationBuilder, Configuration}

/**
 * Created by patrick on 21/01/15.
 */
package object twitter4s {

  implicit def appConfig2TwitterConfig(appConfig: TwitterAppConfig): Configuration = {
    val configBuilder = new ConfigurationBuilder()
    configBuilder.setOAuthAccessToken(appConfig.accessToken)
    configBuilder.setOAuthAccessTokenSecret(appConfig.accessTokenSecret)
    configBuilder.setOAuthConsumerKey(appConfig.consumerKey)
    configBuilder.setOAuthConsumerSecret(appConfig.consumerSecret)
    configBuilder.build()
  }

}
