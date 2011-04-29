package com.codahale.jerkson

import org.codehaus.jackson.map.ObjectMapper

private[jerkson] class LeakyObjectMapper extends ObjectMapper {
  private[jerkson] def serializerFactory = _serializerFactory
}
