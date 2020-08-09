package learnscala.tapirhttp4s.data

import enumeratum.{CirceEnum, Enum, EnumEntry}
import enumeratum.EnumEntry.Lowercase
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import sttp.tapir.codec.enumeratum.TapirCodecEnumeratum

sealed trait MathOp extends EnumEntry with Lowercase
object MathOp extends Enum[MathOp] with CirceEnum[MathOp] with TapirCodecEnumeratum {
  case object Plus extends MathOp
  case object Minus extends MathOp
  case object Mul extends MathOp
  case object Div extends MathOp
  case object Root extends MathOp

  val values = findValues
}

case class MathRequest(
    x: Int,
    y: Option[Int]
)

object MathRequest {
  implicit val encode: Encoder[MathRequest] = deriveEncoder[MathRequest]
  implicit val decode: Decoder[MathRequest] = deriveDecoder[MathRequest]
}