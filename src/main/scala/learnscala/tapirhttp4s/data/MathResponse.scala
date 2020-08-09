package learnscala.tapirhttp4s.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class MathResponse(
    result: String
)

object MathResponse {
  implicit val encode: Encoder[MathResponse] = deriveEncoder[MathResponse]
  implicit val decode: Decoder[MathResponse] = deriveDecoder[MathResponse]
}