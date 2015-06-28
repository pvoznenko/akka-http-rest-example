package InstagramPicsFilter

import org.scalatest.{ Matchers, WordSpec }
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._

class ApplicationSpec extends WordSpec with Matchers with Server with ScalatestRouteTest {
  val routes = Routes.paths

  "return a greeting for GET requests to the root path" in {
    Get() ~> routes ~> check {
      responseAs[String] should include("Hello World!")
    }
  }

  "leave GET requests to other paths unhandled" in {
    Get("/kermit") ~> routes ~> check {
      handled shouldBe false
    }
  }

  "return a MethodNotAllowed error for PUT requests to the root path" in {
    Put() ~> Route.seal(routes) ~> check {
      status === StatusCodes.MethodNotAllowed
      responseAs[String] shouldEqual "HTTP method not allowed, supported methods: GET"
    }
  }
}