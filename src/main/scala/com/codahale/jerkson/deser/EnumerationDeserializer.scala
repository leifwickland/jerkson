package com.codahale.jerkson.deser

import org.codehaus.jackson.`type`.JavaType
import org.codehaus.jackson.JsonParser
import org.codehaus.jackson.map.{DeserializationContext, JsonDeserializer, DeserializerProvider, DeserializationConfig}

class EnumerationDeserializer(config: DeserializationConfig,
                              javaType: JavaType,
                              provider: DeserializerProvider) extends JsonDeserializer[Object] {

  def deserialize(jp: JsonParser, ctxt: DeserializationContext) = {
    println(javaType.getRawClass)
    println(javaType.getRawClass.getFields.toList)
    try {
      val m = javaType.getRawClass.getMethod("withName", classOf[String])
      println(m)
      m.invoke(javaType.getRawClass, jp.getText)
    } catch {
      case e: NoSuchMethodException => {
        e.printStackTrace()
        null
      }
    }
  }
}
