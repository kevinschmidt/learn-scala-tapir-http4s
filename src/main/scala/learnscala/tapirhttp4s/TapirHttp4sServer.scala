package learnscala.tapirhttp4s

import cats.effect.{ConcurrentEffect, ContextShift, Timer}
import fs2.Stream
import learnscala.tapirhttp4s.endpoints.MathEndpoint
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.implicits._
import sttp.tapir.docs.openapi._
import sttp.tapir.openapi.circe.yaml._
import org.http4s.server.Router
import org.http4s.server.middleware.Logger
import sttp.tapir.swagger.http4s.SwaggerHttp4s

import scala.concurrent.ExecutionContext.global

object TapirHttp4sServer {
  def stream[F[_]: ConcurrentEffect: ContextShift: Timer]: Stream[F, Nothing] = {
    val httpApp = Router(
      "/" -> MathEndpoint[F].routes,
      "/" -> new SwaggerHttp4s(
        List(MathEndpoint.calculateMathEndpoint)
          .toOpenAPI("math", "1.0")
          .toYaml
      ).routes
    ).orNotFound
    BlazeServerBuilder[F](global)
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(Logger.httpApp(true, true)(httpApp))
      .serve
      .drain
  }
}
