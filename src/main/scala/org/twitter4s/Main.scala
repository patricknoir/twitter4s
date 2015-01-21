package org.twitter4s

/**
 * Created by patrick on 21/01/15.
 */
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
