package learnscala.tapirhtt4ps.endpoints

import cats.effect.{ContextShift, IO}
import learnscala.tapirhttp4s.endpoints.MathEndpoint
import org.http4s.{Method, Request, Uri}
import org.specs2.Specification
import org.specs2.concurrent.ExecutionEnv
import org.http4s.implicits._

class MathEndpointSpec extends Specification {
  implicit val contextShift: ContextShift[IO] = IO.contextShift(ExecutionEnv.fromGlobalExecutionContext.executionContext)

  def is = sequential ^
    s2"""
    MathEndpoint
    ======================

    calculate
      plus $plus

    """

  val mathEndpoint = MathEndpoint[IO]().routes

  def plus = {
    val request = Request[IO](Method.POST, Uri(path = "/math/plus/calculate")).withEntity("""{"x":10,"y":78}""")
    mathEndpoint.orNotFound(request).unsafeRunSync().as[String].unsafeRunSync() must_== ("""{"result":"88"}""")
  }
}