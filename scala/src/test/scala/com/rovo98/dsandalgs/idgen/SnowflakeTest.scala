package com.rovo98.dsandalgs.idgen

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import java.util.Date

class SnowflakeTest extends AnyFunSuite with Matchers {

  test("snowflake id generation example.") {
    val snowflakeIdGenerator = Snowflake(1, 1)
    for (_ <- 0 to 10) {
      val id = snowflakeIdGenerator.nextId()
      println(id)
      val binaryString = id.toBinaryString.leftPadding(64, "0")
      println(binaryString.snowflakeFormat())
    }
  }

  test("test overflow case") {
    import java.lang.{Long => jLong}
    // the max timestamp value that snowflake id can hold
    val maxTs: Long = jLong.parseLong("1" * 41, 2)
    println(s"max timestamp diff - ${maxTs}")

    // example start timestamp to generate id
    println(s"start ts: ${new Date(1288834974657L)}")

    // the max timestamp value in date format
    println(s"max timestamp in date: ${new Date(maxTs)}")

    // 69
    println(2039 - 1970)

    val maxTsShifted = maxTs << 22

    println(s"$maxTs -" +
      s" binary representation->${maxTsShifted.toBinaryString.leftPadding(64, "0").snowflakeFormat()}")
    var ts: Long = maxTsShifted << 1

    println(s"left shift 1 bit: $ts")
    println(ts.toBinaryString.leftPadding(64, "0"))

    ts = ts & (~(1L << 63))

    // set first bit as 0
    println((~(1L << 63)).toBinaryString.leftPadding(64, "0"))
    println(s"processed ${ts}")
    println(ts.toBinaryString.leftPadding(64, "0").snowflakeFormat())
  }

  implicit class RichString(val str: String) {
    def leftPadding(num: Int, s: String): String = {
      if (str.length >= num) return s
      (s * (num - str.length)).concat(str)
    }

    def snowflakeFormat(): String = {
      require(str.length == 64)
      s"${str.charAt(0)}-${str.substring(1, 42)}" +
        s"-${str.substring(42, 47)}-${str.substring(47, 52)}" +
        s"-${str.substring(52, 64)}"
    }
  }
}

