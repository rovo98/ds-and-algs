package com.rovo98.dsandalgs.bloomfilter

import com.rovo98.dsandalgs.function.hash.HashFunction

/**
 * @author rovo98
 */
trait BloomFilter[T] {
  def add(elem: T): Unit

  def contains(elem: T): Boolean
}

class ClassicalBloomFilter[CT](private val hashFunctions: Array[HashFunction[CT]],
                               private val m: Int
                              ) extends BloomFilter[CT] {
  private val _bitVector: Array[Int] = new Array[Int](m)

  def this(set: Array[CT], hashFunctions: Array[HashFunction[CT]], m: Int) = {
    this(hashFunctions, m)
    // initialize the bloom filter.
    for (i <- set.indices) {
      for (j <- this.hashFunctions.indices)
        _bitVector(this.hashFunctions(j) invoke set(i)) = 1
    }
  }

  override def add(elem: CT): Unit =
    for (i <- this.hashFunctions.indices)
      _bitVector(this.hashFunctions(i) invoke elem) = 1

  override def contains(elem: CT): Boolean = {
    for (i <- this.hashFunctions.indices) {
      if (_bitVector(this.hashFunctions(i) invoke elem) != 1) return false
    }
    true
  }
}

object ClassicalBloomFilter {
  def apply[CT](hashFunctions: Array[HashFunction[CT]], m: Int): ClassicalBloomFilter[CT] =
    new ClassicalBloomFilter(hashFunctions, m)

  def apply[CT](elements: Array[CT], hashFunctions: Array[HashFunction[CT]], m: Int): ClassicalBloomFilter[CT] =
    new ClassicalBloomFilter(elements, hashFunctions, m)
}

class ImprovedBloomFilter[IT]() extends BloomFilter[IT] {
  override def add(elem: IT): Unit = ???

  override def contains(elem: IT): Boolean = ???
}

object ImprovedBloomFilter {
  def apply[IT](): ImprovedBloomFilter[IT] = ???
}
