package tao.finance.funds.exposition

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import tao.finance.funds.domain.shanghaistock.FundRegistryActor
import tao.finance.funds.exposition.route.FundRoute

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object WebServer extends App with FundRoute{

    implicit val system: ActorSystem = ActorSystem("helloAkkaHttpServer")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val fundRegistryActor: ActorRef = system.actorOf(FundRegistryActor.props, "fundRegistryActor")

    lazy val routes: Route = fundRoute

    //#http-server
    Http().bindAndHandle(routes, "localhost", 8080)

    println(s"Server online at http://localhost:8080/")

    Await.result(system.whenTerminated, Duration.Inf)
}
