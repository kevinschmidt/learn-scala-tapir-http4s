package learnscala.tapirhttp4s.endpoints

import cats.effect.{ContextShift, Sync}
import learnscala.tapirhttp4s.data.{MathOp, MathRequest, MathResponse}
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import sttp.tapir.Endpoint
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.server.http4s._

case class MathEndpoint[F[_]: Sync: ContextShift]() extends Http4sDsl[F] {
  def calculateMath(op: MathOp, req: MathRequest): F[Either[String, MathResponse]] = Sync[F].delay {
    Right(MathResponse(op match {
      case MathOp.Plus => (req.x + req.y.getOrElse(0)).toString
      case MathOp.Minus => (req.x - req.y.getOrElse(0)).toString
      case MathOp.Mul => (req.x * req.y.getOrElse(0)).toString
      case MathOp.Div => (req.x / req.y.getOrElse(0)).toString
      case MathOp.Root => Math.sqrt(req.x).toString
    }))
  }

  def routes: HttpRoutes[F] = MathEndpoint.calculateMathEndpoint.toRoutes((calculateMath _).tupled)
}

object MathEndpoint {
  val calculateMathEndpoint: Endpoint[(MathOp, MathRequest), String, MathResponse, Nothing] =
    endpoint
      .in("math" / path[MathOp]("operation") / "calculate")
      .post
      .description("make a simple math calculation")
      .errorOut(stringBody)
      .in(jsonBody[MathRequest])
      .out(jsonBody[MathResponse])
}