package learnscala.tapirhttp4s

import cats.effect.{ConcurrentEffect, ContextShift, Timer}
import fs2.Stream
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.middleware.Logger

import scala.concurrent.ExecutionContext.global

object TapirHttp4sServer {
  def stream[F[_]: ConcurrentEffect: ContextShift: Timer]: Stream[F, Nothing] = {
    val httpApp = Router().orNotFound
    BlazeServerBuilder[F](global)
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(Logger.httpApp(true, true)(httpApp))
      .serve
      .drain
  }
}
