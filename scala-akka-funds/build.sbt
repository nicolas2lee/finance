
import Dependencies._
import sbt.Keys._

ThisBuild / scalaVersion := "2.12.4"
ThisBuild / sbtVersion := "1.2.8"

lazy val root = project.aggregate(domain,  infra, exposition)

lazy val domain = project.settings(
  libraryDependencies ++=
    Seq(Akka.actor, Akka.stream)
)

lazy val infra = project.settings(
  libraryDependencies ++=
    Seq(Akka.actor, Akka.stream, Akka.http)
)
  .dependsOn(domain)

lazy val exposition = project.settings(
  libraryDependencies ++=
    Seq(Akka.actor, Akka.stream, Akka.http, Akka.httpTestKit)
  )
  .dependsOn(domain, infra)





