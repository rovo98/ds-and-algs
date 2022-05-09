package com.rovo98.dsandalgs.idgen

/**
 * twitter snowflake id generator
 *
 * |               41 bits                      |  5bits |  5bits |  12 bits   |
 * 0-0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * | ---------- milliseconds -------------------|  datacenterId| workerId| sequence no |
 *
 * @param workerId     id of the worker node (0-31)
 * @param dataCenterId id of the date center (0-31)
 */
class Snowflake(var workerId: Long, var dataCenterId: Long) {
  // system start timestamp (UTC 2017-06-28 00:00:00)
  private val startTime: Long = 1498608000000L

  // num of bits that workId can occupied
  private val workerIdBits: Long = 5L

  // num of bits that datacenterId can occupied
  private val dataCenterIdBits: Long = 5L

  // 31
  private val maxWorkerId: Long = -1L ^ (-1L << workerIdBits)
  // 31
  private val maxDatacenterId: Long = -1L ^ (-1L << dataCenterIdBits)

  // num of bits that sequence no can occupied
  private val sequenceBits: Long = 12L

  // workerId left shift bits - 12
  private val workerIdLeftShift = sequenceBits

  // datacenterId left shift bits - 17(12+5)
  private val dataCenterIdLeftShift = sequenceBits + workerIdBits

  // timestamp left shift bits - 22 (5+5+12)
  private val timestampLeftShift = sequenceBits + dataCenterIdBits + workerIdBits

  // mask for the generated sequence no (4095)
  private val sequenceMask: Long = -1L ^ (-1L << sequenceBits)

  // current no of generated id
  private var sequence: Long = 0L
  // timestamp for the last generated id
  private var lastTimestamp = -1L

  // preconditions checking
  require(workerId <= maxWorkerId && workerId > 0, s"Worker id can't be greater than $maxWorkerId")
  require(dataCenterId <= maxDatacenterId && dataCenterId > 0, s"datacenter id can't be greater than $maxDatacenterId")

  def nextId(): Long = {
    this.synchronized {
      var timestamp = currentTime()
      // system clock abnormal
      if (timestamp < lastTimestamp) {
        throw new RuntimeException(
          f"Clock moved backwards. Refusing to generate id for ${lastTimestamp - timestamp}%d milliseconds.")
      }
      if (lastTimestamp == timestamp) {
        sequence = (sequence + 1) & sequenceMask
        // sequence overflow > 4095
        if (sequence == 0) {
          // block to get new timestamp
          timestamp = blockTillNextMillis(lastTimestamp)
        }
      } else {
        sequence = 0L
      }
      lastTimestamp = timestamp

      ((timestamp - startTime) << timestampLeftShift) |
        (dataCenterId << dataCenterIdLeftShift) |
        (workerId << workerIdLeftShift) |
        sequence
    }
  }

  private def blockTillNextMillis(ts: Long): Long = {
    var timestamp = currentTime()
    while (timestamp <= ts) {
      timestamp = currentTime()
    }
    timestamp
  }

  private def currentTime(): Long = System.currentTimeMillis()
}

object Snowflake {
  def apply(workerId: Long, dataCenterId: Long): Snowflake = new Snowflake(workerId, dataCenterId)
}
