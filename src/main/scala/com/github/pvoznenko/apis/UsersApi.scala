package com.github.pvoznenko.apis

import akka.http.scaladsl.server.Directives._
import com.github.pvoznenko.utils.BearerTokenGenerator

trait UsersApi extends BearerTokenGenerator {
  val usersRoutes =
    (path("users" / "authentication") & post) {
      complete(generateSHAToken("InstagramPicsFilter"))
    }
}
