Twitter4s
=========

Introduction
------------
Twitter4s is a simple library designed for scala to consumer the twitter api.
The library is built on top of the more notorious Twitter4J and wrapping the API behind the Netflix Rx framework.

Twitter4s provides a singleton factory to create observable on twitter streams.

This is a simple usage example:
```scala
object Main extends App {

  val observable = Twitter4s.create(TwitterAppConfig.fromDefaultConfig()).stream().sample()

  val subscription = observable.subscribe { status =>
    println(status.getText)
  }

  println("Press enter to terminate")
  readLine()

  subscription.unsubscribe()

  println("Terminated")
}
```

### 1. Configuration
To create a twitter observable you need to provide the TwitterAppConfig object.
This object represents your Twitter App credentials.

Each registered application at twitter will have:

AccessToken
AccessTokenSecret
ConsumerKey
ConsumerSecret

The TwitterAppConfig is the object which will contain all the above credential and
need to be provided to the Twitter4j object in order to create your observable.

You can create an instance of TwitterAppConfig by:

Explicitly create the object

```scala
val appConfig = TwitterAppConfig("<consumer key>", "<consumer secret>", "<access token>", "<access token secret>")
```

From a typesafe config file application.conf:

```
twitter4s {
  appConfig {
    oauth.consumerKey="<consumer key>"
    oauth.consumerSecret="<consumer secret>"
    oauth.accessToken="<access token>"
    oauth.accessTokenSecret="<access token secret>"
  }
}
```

and instantiated as:

```scala
val appConfig = TwitterAppConfig.fromDefaultConfig()
```