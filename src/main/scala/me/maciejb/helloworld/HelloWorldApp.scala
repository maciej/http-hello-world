package me.maciejb.helloworld

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import akka.http.scaladsl.server.Directives._

object HelloWorldApp extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val route = get {
    complete {
      "Hello, world!"
    }
  }

  val config = ServerConfig.build()

  val bindingFuture = Http().bindAndHandle(route, config.host, config.port)

}

case class ServerConfig(host: String, port: Int)

object ServerConfig {
  def build(): ServerConfig = Default

  def Default = ServerConfig("0.0.0.0", 8080)
}
