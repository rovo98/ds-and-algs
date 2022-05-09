package com.rovo98.dsandalgs.idgen

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SnowflakeTest extends AnyFunSuite with Matchers {

  test("snowflake id generation example.") {
    val snowflakeIdGenerator = Snowflake(1, 1)
    for (_ <- 0 to 100) {
      println(snowflakeIdGenerator.nextId())
    }
  }
}
