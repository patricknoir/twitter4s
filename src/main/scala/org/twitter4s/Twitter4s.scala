package org.twitter4s

import rx.lang.scala.{Subscription, Observable}
import org.twitter4s._
import twitter4j._

/**
 * Created by patrick on 21/01/15.
 */
object Twitter4s {
  def create(implicit appConfig:TwitterAppConfig): TwitterBuilder = TwitterBuilder(appConfig)

  def create(accessToken:String, accessTokenSecret:String, consumerKey:String, consumerSecret:String): TwitterBuilder = create(TwitterAppConfig(accessToken, accessTokenSecret, consumerKey, consumerSecret))
}

case class TwitterBuilder(appConfig:TwitterAppConfig) {
  def stream(): TwitterStreamBuilder = TwitterStreamBuilder(appConfig)
}

case class TwitterStreamBuilder(appConfig:TwitterAppConfig) {

  private[this] def createStream(run: TwitterStream => Unit): Observable[Status] = Observable.create[Status] { observer =>

    val factory = new TwitterStreamFactory(appConfig)

    val twitterStream = factory.getInstance()

    val listener = new StatusAdapter {
      override def onStatus(status: Status) = observer.onNext(status)
      override def onException(ex: Exception) = observer.onError(ex)
    }
    twitterStream.addListener(listener)

    run(twitterStream)


    Subscription {
      twitterStream.removeListener(listener)
      twitterStream.shutdown()
    }
  }

  def sample(): Observable[Status] = createStream(_.sample())
  def filter(keyWords:Array[String], languages: Array[String] = Array.empty[String]): Observable[Status] = createStream {
    val query = new FilterQuery().language(languages)
    query.track(keyWords)
    _.filter(query)
  }
  def user(track:Array[String] = Array.empty[String]): Observable[Status] = createStream { twitterStream =>
    if(track.size == 0)
      twitterStream.user()
    else
      twitterStream.user(track)
  }
}