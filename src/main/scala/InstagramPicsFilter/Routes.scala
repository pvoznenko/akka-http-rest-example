package InstagramPicsFilter

import akka.http.scaladsl.server.Directives._

object Routes {

  val paths = logRequestResult("akka-http-microservice") {
    path("diagnostics" / "heartbeat") {
      get {
        complete {
          "Ok!"
        }
      }
    } ~
      path("index" / LongNumber) { shardSequenceId =>
        get {
          complete(
            "Indexing in progress..."
          )
        }
      } ~
      path("index" / LongNumber / LongNumber / IntNumber) { (start, end, parallelism) =>
        get {
          complete(
            "Indexing in progress..."
          )
        }
      } ~
      path("")(getFromResource("public/index.html"))
  }
}
