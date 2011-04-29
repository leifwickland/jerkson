package com.codahale.jerkson

import org.codehaus.jackson.JsonFactory
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.`type`.JavaType
import org.codehaus.jackson.map.`type`._

trait Factory {
  /**
   * The ObjectMapper to be used by {@link Parser} and {@link Generator}.
   */
  protected val mapper: ObjectMapper

  /**
   * The JsonFactory to be used by {@link Parser} and {@link Generator}.
   */
  protected val factory: JsonFactory

  private[jerkson] def parametricType[A](implicit mf: Manifest[A]): JavaType = {
    mf.erasure match {
      case klass: Class[_] if klass.isArray =>
        ArrayType.construct(SimpleType.construct(klass.getComponentType))
      case klass: Class[_] if classOf[Seq[_]].isAssignableFrom(klass) =>
        CollectionLikeType.construct(klass, parametricType(mf.typeArguments.head))
      case klass: Class[_] if classOf[Set[_]].isAssignableFrom(klass) =>
        CollectionLikeType.construct(klass, parametricType(mf.typeArguments.head))
      case klass: Class[_] if classOf[java.util.Collection[_]].isAssignableFrom(klass) =>
        CollectionType.construct(klass, parametricType(mf.typeArguments.head))
      case klass: Class[_] if classOf[Map[_, _]].isAssignableFrom(klass) =>
        MapLikeType.construct(klass, parametricType(mf.typeArguments.head), parametricType(mf.typeArguments.tail.head))
      case klass: Class[_] if classOf[java.util.Map[_, _]].isAssignableFrom(klass) =>
        MapType.construct(klass, parametricType(mf.typeArguments.head), parametricType(mf.typeArguments.tail.head))
      case klass => SimpleType.construct(klass)
    }
  }

}
