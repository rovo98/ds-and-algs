package com.rovo98.dsandalgs.function.diffamplify

/**
 * @author rovo98
 */
trait DifferenceAmplifyFunc[T] {
  def amplify(t: T): String
}

object DifferenceAmplifyFunc {
  implicit object StringDiffAmplifyFunc extends DifferenceAmplifyFunc[String] {
    override def amplify(t: String): String = ???
  }

  def amplify[T: DifferenceAmplifyFunc](t: T): String = {
    implicitly[DifferenceAmplifyFunc[T]].amplify(t)
  }
}
