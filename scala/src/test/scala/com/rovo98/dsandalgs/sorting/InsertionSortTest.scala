package com.rovo98.dsandalgs.sorting

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class InsertionSortTest extends AnyFunSuite with Matchers {
  test("case 1 - asc") {
    val list = 1 :: 3 :: 4 :: 2 :: 5 :: Nil
    val expected = List(1, 2, 3, 4, 5)

    val sorted = InsertionSort.sort(list)
    sorted shouldEqual expected
  }

  test("case 2 - desc") {
    val list = 1 :: 3 :: 4 :: 2 :: 5 :: Nil
    val expected  = List(5, 4, 3, 2, 1)

    val sorted = InsertionSort.sort(list)(Ordering.Int.reverse)
    sorted shouldEqual expected
  }

  test("case 3") {
    val list = List("rovo98", "hello", "world", "test")
    val expected = "hello" :: "rovo98" :: "test" :: "world" :: Nil

    val sorted = InsertionSort.sort(list)
    sorted shouldEqual expected
  }
}
