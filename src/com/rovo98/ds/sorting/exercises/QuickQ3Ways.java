package com.rovo98.ds.sorting.exercises;

/**
 * 实现快速三向切分 (J.Bently, D.Mcllroy)
 * <p>
 * 将重复元素放置在子数组的两端的方式实现一个信息量最优的排序算法。
 * 使用两个索引 p 和 q, 使得a[lo .. p-1] 和 [q+1 .. hi] 的元素
 * 都和 a[lo] 相等.
 *
 * 使用另外两个索引 i 和 就， 使得 a[p .. i-1]小于a[lo], a[j+1 .. q]
 * 大于a[lo]。
 *
 * 在内循环加入代码，在a[i] 和 v 相当时将其和a[p]交换（并p+1)，
 * 在a[j] 和 v 相等且 a[i] 和 a[j] 尚未和v 进行比较之前将其与a[q]交换。
 *
 *
 *
 *
 * Exercise from algs4.
 *
 * @author rovo98
 *
 * Date: 10/3/2018
 */
public class QuickQ3Ways {

    @SuppressWarnings("unchecked")
    private static void sort(Comparable[] a, int lo, int hi) {

    }
}
