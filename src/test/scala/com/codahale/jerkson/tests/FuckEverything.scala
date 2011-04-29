package com.codahale.jerkson.tests

import com.codahale.jerkson.Json

object FuckEverything {
  def main(args: Array[String]) {
    println(Json.generate(Vector(1, 2, 3)))
    println(Json.parse[Vector[Int]]("[1,2,3]"))
  }
}
