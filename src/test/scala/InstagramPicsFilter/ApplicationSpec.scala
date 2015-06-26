package InstagramPicsFilter

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{StatusCodes, HttpRequest}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.scaladsl.{Sink, Source}
import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Second, Milliseconds, Span, Seconds}
import org.scalatest.{BeforeAndAfterAll, Matchers, FlatSpec}
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContext.Implicits.global

class ApplicationSpec extends FlatSpec with Matchers with ScalaFutures with BeforeAndAfterAll with Server {
  implicit val testSystem = ActorSystem("instagram-pics-filter-test")
  implicit val testMaterializer = ActorMaterializer()

  override def beforeAll() = start()

  override def afterAll() = testSystem.shutdown()

  def sendRequest(req: HttpRequest) =
    Source.single(req).via(
      Http().outgoingConnection(host = config.getString("http.host"), port = config.getInt("http.port"))
    ).runWith(Sink.head)

  "The app" should "return index.html on a GET to /" in {
    val request = sendRequest(HttpRequest())

    whenReady(request, Timeout(Span(1, Second))) { response =>
      response.status shouldBe StatusCodes.OK

      val stringFuture = Unmarshal(response.entity).to[String]

      whenReady(stringFuture) { str =>
        str should include("Hello World!")
      }
    }
  }
  "The app" should "return 404 on a GET to /foo" in {
    val request = sendRequest(HttpRequest(uri = "/foo"))

    whenReady(request) { response =>
      response.status shouldBe StatusCodes.NotFound
    }
  }
}