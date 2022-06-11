package com.rovo98.dsandalgs.sorting

/**
 * InsertionSort naive implementation using List concatenation.
 * @author rovo98
 */
object InsertionSort {

  /**
   * Insertion sort implementation using List concatenation
   *
   * @param xs A list of elements to be sorted
   * @param ordering [[Ordering]] defines the way to sort elements in asc/desc
   * @tparam T The type of the element
   * @return The sorted List
   */
  def sort[T](xs: List[T])(implicit ordering: Ordering[T]): List[T] =
    xs match {
      case List() => List[T]()
      case y :: ys => insert(y, sort(ys))
    }

  private def insert[T](x: T, xs: List[T])(implicit ordering: Ordering[T]): List[T] =
    xs match {
      case List() => x :: Nil
      case y :: ys =>
        if (implicitly[Ordering[T]].lt(x, y)) x :: y :: ys
        else y :: insert(x, ys)
    }
}
