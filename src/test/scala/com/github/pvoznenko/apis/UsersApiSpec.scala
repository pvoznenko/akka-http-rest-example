package com.github.pvoznenko.apis

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class UsersApiSpec extends WordSpec with Matchers with ScalatestRouteTest with UsersApi {
  "return a greeting for GET requests to the root path" in {
    Post("/users/authentication") ~> usersRoutes ~> check {
      val response = responseAs[String]

      response should not be ""
      response.length should be(64)
    }
  }
}