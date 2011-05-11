package com.codahale.jerkson.ser

import org.codehaus.jackson.JsonGenerator
import org.codehaus.jackson.map.{SerializerProvider, JsonSerializer}


class EnumerationSerializer extends JsonSerializer[Enumeration#Value] {
  def serialize(value: Enumeration#Value, json: JsonGenerator,
                provider: SerializerProvider) {
    json.writeString(value.toString)
  }
}
