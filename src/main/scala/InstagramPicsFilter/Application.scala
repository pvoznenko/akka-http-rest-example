package InstagramPicsFilter

import akka.http.scaladsl.Http
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

trait Server {
  private implicit val system = ActorSystem("instagram-pics-filter")
  private implicit val materializer = ActorMaterializer()

  val config = ConfigFactory.load()

  def start() = Http()
    .bindAndHandle(handler = Routes.paths, interface = config.getString("http.host"), port = config.getInt("http.port"))
}

object Application extends App with Server {
  start()
}