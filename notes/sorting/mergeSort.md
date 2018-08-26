---
author: rovo98
description: notes, learning from algs4.
date: 5/3/2018
---

# MergeSort - 归并排序

## Table of contents

- [引入](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/mergeSort.md#引入)
- [原始归并的抽象方法](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/mergeSort.md#原地归并的抽象方法----go-back-to-top)
- [自顶向下的归并排序](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/mergeSort.md#自顶向下的归并排序----go-back-to-top)
- [自底向上的归并排序](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/mergeSort.md#自底向上的归并排序----go-back-to-top)

### 引入

归并排序属于**分治算法(Divide and Conquer)**。通过递归不断把待排序数组分成两个部分，将有序的两部分再重新归并一起来实现对数组的排序。

**[pseudo code for 2-way merge sort]**:

```txt
MergeSort(arr[], aux[], lo, hi)
If hi > lo
	1. Find the middle point to divide the array into two halves;
		int mid = lo + (hi - lo) / 2;
	2. Call MergeSort for first half;
		MergeSort(arr, aux, lo, mid);
	3. Call MergeSort for second half;
		MergeSort(arr, aux, mid+1, hi);
	4. Merge the two halves sorted in step 2 and 3;
		Call merge(arr, aux, lo, mid, hi);
```
### 原地归并的抽象方法 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/mergeSort.md#mergesort---归并排序)

要实现上述的归并方法 -- ```merge()```很简单，创建一个数组将需要归并的**原数组的两个部分**中的元素放到这个数组中，然后再归并按大小顺序放回原数组。
>但是，这里我们要考虑一个问题，当我们用归并排序对一个比较大的数组进行排序时，我们需要进行很多次归并，因此在每一次归并时都创建一个新的数组来存储排序结果会带来问题。我们可以考虑只创建一个**辅助数组```aux[]```**, 在每一个归并时，将原数组需要归并的两个部分复制到```aux[]```中，再把归并结果放回原数组。

```java
public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
	// precondition: a[lo .. mid] and a[mid+1 .. hi] is sorted.
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid+1, hi);
    
	// copy a[lo .. hi] to aux[lo .. hi].
    for (int k = lo; k <= hi; k++) {
    	aux[k] = a[k];
    }
    int i = lo;
    int j = mid+1;
    
    for (int k = lo; k <= hi; k++) {
    	if      (i > mid)               a[k] = aux[j++];
        else if (j > hi)                a[k] = aux[i++];
        else if (less(aux[j], aux[i]))  a[k] = aux[j++];
        else 			               a[k] = aux[i++];
    }
    // postcondition: a[lo .. hi] is sorted.
    assert isSorted(a, lo, hi);
}
```

[说明] : 该方法先将原数组两个需要归并的部分复制到```aux[]```中，然后**从两个部分的起始位置开始取元素，一直选择两个部分中较小的元素放回原数组中，当有一部分元素取完了，则将另一部分剩余的元素全部放回原数组**。

原地归并抽象方法轨迹： [from algs4]

![原地归并抽象方法轨迹](https://github.com/rovo98/ds-and-algs/blob/master/images/sorting/mergeSort_merge.png)

### 自顶向下的归并排序 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/mergeSort.md#mergesort---归并排序)

```java
public static void mergeSort(Comparable[] a) {
	int n = a.length;
    Comparable[] aux = new Comparable[n];
    sort(a, aux, 0, a.length-1);
}
private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
	// If array size is 1 then return.
	if (hi <= lo)    return;
    // Find the middle point to divide array into two halves.
    int mid = lo + (hi - lo) / 2;
    // Call the mergeSort for first half.
    sort(a, aux, lo, mid);
    // Call the mergeSort for second half.
    sort(a, aux, mid+1, hi);
    // Merge two halves sorted.
    merge(a, aux, lo, mid, hi);
}
```

自顶向下归并结果轨迹 : [from algs4]

![自顶向下归并结果轨迹](https://github.com/rovo98/ds-and-algs/blob/master/images/sorting/mergeSort_ubSort_1.png)

自顶向下归并排序调用轨迹 : [from algs4]

![自顶向下归并排序调用轨迹](https://github.com/rovo98/ds-and-algs/blob/master/images/sorting/mergeSort_ubSort_2.png)

```txt
命题 F: 对于长度为 N 的任意数组， 自顶向下归并排序需要 1/2NlgN 至 NlgN 次比较。

自顶向下的所需时间表达式为 : T(N) = 2T(N/2) + O(n)使用解决
递归式的 Master method 解得 T(N) = $\Theta(n log n)$
```

我们可以通过下图来理解命题F, 每个节点都表示一个sort()方法通过merge()方法归并而成的子数组。**这棵树正好有 n 层。对于0 ~ n-1 之间的任意 k, 自顶向下的第 k 层有 $2^k$个子数组，每个数组长度为 $2^{n-k}$, 归并最多需要 $2^{n-k}$ 次比较。**因此每层的比较次数为$2^k X 2^{n-k} = 2^n$, n 层总共为 $n2^n = NlgN$。

子数组树状图 : [from algs4]

![子数组树状图](https://github.com/rovo98/ds-and-algs/blob/master/images/sorting/mergeSort_ubSort_3.png)

```txt
命题 G : 对于长度为 N 的任意数组，自顶向下的归并排序最多需要
访问数组 6NlgN 次。

证明： 每次归并最多需要访问数组 6N 次（2N 次用来复制， 2N次
用来将排好序的元素移动回去，另外最多比较2N次），根据命题F即
可得到这个命题的结果。
```

**[小结]**: 归并排序排序算法的缺点是辅助数组所使用的额外空间和N的大小成正比。一些能够大幅度缩短归并排序的运行时间的想法：

- 对小规模子数组进行插入排序；
- 测试数组是否已经有序；
- 不将元素复制到辅助数组中。


### 自底向上的归并排序 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/mergeSort.md#mergesort---归并排序)

实现归并排序另外一种方法是**先归并那些微型数组， 然后再成对归并得到的子数组，直到将整个数组归并在一起**。

```java
public static void MergeBU(Comparable[] a) {
	int n = a.length;
    Comparable[] aux = new Comparable[n];
    
    for (int sz = 1; sz < n; sz += sz) {
    	for (int lo = sz; lo < n-sz; lo += sz+sz) {
        	merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, n-1));
        }
    }
{
```

[注] : 自底向上的归并排序会多次遍历整个数组，根据子数组大小进行两两归并。子数组的大小 sz 的初始值为 1， 每次加倍。最后一个子数组的大小只有在数组大小是 sz 的偶数倍的时候才会等于 sz(否则它会比sz小)。

自底向上归并排序结果轨迹 ： [from algs4]

![自底向上归并排序结果轨迹](https://github.com/rovo98/ds-and-algs/blob/master/images/sorting/mergeSort_buSort_1.png)

```txt
命题 H : 对于长度为 N 的任意数组， 自底向上的归并排序需要 
1/2NlgN 至 NlgN次比较，最多访问数组 6NlgN 次。

证明 ： 处理一个数组的遍数正好是 [lgN] (即 2^n <= N <
2^n+1 中的 n)。每一遍会访问数组6N次。
比较次数在 N/2 和 N 之间。
```

【补充】： 当数组长度为 2 的幂时，自顶向下和自底向上的归并排序所用的比较次数和数组访问次数正好相同，只是顺序不同。其他时候，两种方法的比较和数组访问的次序会有所不同。

