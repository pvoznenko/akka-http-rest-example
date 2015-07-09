package com.github.pvoznenko

import akka.http.scaladsl.server.Directives._
import com.github.pvoznenko.apis.UsersApi

trait Routes extends UsersApi {
  val routes = pathPrefix("v1") {
    usersRoutes
  } ~ path("")(getFromResource("public/index.html"))
}
