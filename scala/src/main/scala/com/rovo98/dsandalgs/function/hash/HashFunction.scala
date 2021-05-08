package com.rovo98.dsandalgs.function.hash

trait HashFunction[T] {
  /**
   * hashing the obj to numeric value
   *
   * @param obj the given object to be hashed.
   * @return a numeric value return by the hash function.
   */
  def invoke(obj: T): Int
}
