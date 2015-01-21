name := "twitter4scala"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.2.1",
  "org.twitter4j" % "twitter4j-core" % "4.0.2",
  "org.twitter4j" % "twitter4j-stream" % "4.0.2",
  "com.netflix.rxjava" % "rxjava-scala" % "0.20.7"
)

    