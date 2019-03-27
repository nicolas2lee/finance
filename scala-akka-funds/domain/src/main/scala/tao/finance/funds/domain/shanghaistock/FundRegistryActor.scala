package tao.finance.funds.domain.shanghaistock

import akka.actor.{Actor, Props}
import tao.finance.funds.domain.shanghaistock.FundRegistryActor.{GetFund, GetFunds}

object FundRegistryActor{
  final case class GetFunds()
  final case class GetFund(fundCode: String)

  def props: Props = Props[FundRegistryActor]
}

class FundRegistryActor extends Actor {
  override def receive: Receive = {
    case GetFunds => println("get funds")
    case GetFund(fundCode) => println(s"$fundCode")
  }
}
