package tao.finance.funds.exposition.route

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import tao.finance.funds.domain.shanghaistock.FundRegistryActor.{GetFund, GetFunds}
import akka.pattern.ask

import scala.concurrent.duration._

trait FundRoute {

  implicit def system: ActorSystem

  def fundRegistryActor: ActorRef

  lazy val fundRoute: Route =
    pathPrefix("funds") {
      pathEnd {
        concat(
          get {
            // Required by the `ask` (?) method below
            implicit val timeout: Timeout =  Timeout(100.seconds)
            fundRegistryActor ? GetFunds
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
          }
        )
      }
      path(Segment) { fundCode => concat(
          get {
            // Required by the `ask` (?) method below
            implicit val timeout: Timeout =  Timeout(100.seconds)
            fundRegistryActor ? GetFund(fundCode)
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
          }
        )
      }

    }


}
