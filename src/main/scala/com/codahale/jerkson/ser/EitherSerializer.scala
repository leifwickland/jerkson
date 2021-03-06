package com.codahale.jerkson.ser

import org.codehaus.jackson.JsonGenerator
import org.codehaus.jackson.map.{SerializerProvider, JsonSerializer}
import org.codehaus.jackson.map.annotate.JsonCachable

@JsonCachable
class EitherSerializer extends JsonSerializer[Either[_, _]] {
  def serialize(value: Either[_, _], json: JsonGenerator, provider: SerializerProvider) {
    val obj: Object = value match {
      case Left(o) => o.asInstanceOf[Object]
      case Right(o) => o.asInstanceOf[Object]
    }

    val serializer = provider.findValueSerializer(obj.getClass, null)
    serializer.serialize(obj, json, provider)
  }
}
