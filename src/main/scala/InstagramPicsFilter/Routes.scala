package InstagramPicsFilter

import akka.http.scaladsl.server.Directives._

object Routes {
  val paths = logRequestResult("akka-http-microservice") {
    (path("diagnostics" / "heartbeat") & get) {
      complete("Ok!")
    } ~
    (path("index" / LongNumber) & get) { shardSequenceId =>
      complete("Indexing in progress...")
    } ~
    (path("index" / LongNumber / LongNumber / IntNumber) & get) { (start, end, parallelism) =>
      complete("Indexing in progress...")
    } ~
    path("")(getFromResource("public/index.html"))
  }
}
