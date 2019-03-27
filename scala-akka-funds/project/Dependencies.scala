import sbt._

object Dependencies {
  object Version {
    val akka        =    "2.5.21"
    val akkaHttp    =    "10.1.7"
  }

  object Akka {
    val actor            = apply("actor")
    val http             = apply("http", Version.akkaHttp)
    val stream           = apply("stream" )
    val httpTestKit      = apply("http-testkit", Version.akkaHttp) % Test


    private def apply(m: String, v: String = Version.akka) = "com.typesafe.akka" %% s"akka-$m" % v
  }

}