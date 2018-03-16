---
author: rovo98
description: notes for searching algs.
date: 7/3/2018
---

# 基本查找算法 - basical searching algorithms

## Table of contents

- [引入](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#引入)
- [线性查找 - linear search](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#线性查找---linear-search----go-back-to-top)
- [跳跃查找 - jump search](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#跳跃查找---jump-search----go-back-to-top)
- [二分查找 - binary search](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#二分查找---binary-search----go-back-to-top)
- [三分查找 - ternary search](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#三分查找---ternary-search----go-back-to-top)
- [指数查找 - exponential search](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#指数搜索---exponential-search----go-back-to-top)
- [插值查找 - interpolation search](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#插值搜索---interpolation-search----go-back-to-top)


### 引入

基本查找算法，我们只谈**静态查找**的查找算法。

### 线性查找 - linear search -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#基本查找算法---basical-searching-algorithms)

特点： 

- 从头开始遍历数组，一个一个和```key```比较，查找成功则返回索引值。
- 不要求数组是**有序的**。
- 时间复杂度为： $O(n)$.

#### 线性查找原始抽象方法实现如下:

```java
public static int linearSearch(Comparable[] a, Comparable key) {
	for (int i = 0; i < a.length; i++) {
    	if (a[i].compareTo(key) == 0)
        	return i;
    }
    return -1;
}
```

### 跳跃查找 - jump search -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#基本查找算法---basical-searching-algorithms)

特点：

- 要求查找数组**有序**；
- 主要思想是每次跳跃固定量的元素来确定目标元素所在的区间，再使用线性查找在区间上搜索目标元素。
- 时间复杂度为： $O(\sqrt{n})$.

**[notice] : ** 假设我们要在一个有n个元素的数组中搜索某个元素，最坏情况下（**当目标元素为最后个元素时），这个算法要跳跃n/m步，在线性搜索时作m-1次比较。**令 $f(n) = ((n/m) + m-1$.当m = $\sqrt{n}$时，f(n) 取最小值，所以通常情况下，**跳跃查找所使用的固定跳跃步数为 $\sqrt{n}$. 

#### 跳跃查找的原始抽象方法实现如下：

```java
public static int jumpSearch(Comparable[] a, Comparable key) {
	int n = a.length;
    int block_size = Math.floor(Math.sqrt(n));
	int step = block_size;
    // 查找目标元素可能出现的区间
    int prev = 0;
    while (a[Math.min(step, n) - 1].compareTo(key) < 0) {
    	prev = step;
        step += block_size;
        if (prev >= n)
        	return -1;
    }
    // 使用线性查找在确定的区间上查找目标元素
    while (a[prev].compareTo(key) < 0) {
    	prev++;
        if (prev == Math.min(step, n))
        	return -1;
    }
    if (a[prev].compareTo(key) == 0)
    	return prev;
    
    return -1;
}
```

### 二分查找 - binary search -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#基本查找算法---basical-searching-algorithms)

特点：

- 要求数组**有序**。
- 将已经排好序的数组分为两个区间(interval), 把区间的中间元素与```key```比较, 若大于则搜索左区间，若小于则搜索右区间，等于则返回元素的索引。**当区间长度为0时（```key```没出现在数组中)**,返回-1。
- 时间复杂度为 ： $O(nlogn)$.

#### 二分查找原始抽象方法实现如下:

```java
// 非递归实现
public static int binarySearch(Comparable[] a, Comparable key) {
	int lo = 0;
    int hi = a.length - 1;
    while (lo <= hi) {
    	int mid = lo + (hi - lo) / 2;
        if (a[mid].compareTo(key) == 0)
        	return mid;
        else if (a[mid].compareTo(key) > 0)
        	hi = mid - 1;
        else
        	lo = mid + 1;
    }
    return -1;
}
// 递归实现
public static int binarySearchRecursion(Comparable[] a, Comparable key, int lo, int hi) {
	if (lo <= hi) {
    	int mid = lo + (hi - lo) / 2;
        if (a[mid].compareTo(key) == 0)
        	return mid;
        else if (a[mid].compareTo(key) < 0)
        	return binarySearchRecursion(a, key, mid+1, hi);
        else
        	return binarySearchRecursion(a, key, lo, mid-1);
    }
    return -1;
}
```

### 三分查找 - ternary search -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#基本查找算法---basical-searching-algorithms)

特点：

- 三分查找是二分查找的扩展；
- 时间复杂度为 ： $O(nlog_3n)$.

**[notice]:** 虽然看似三分查找的时间复杂度比二分查找的时间复杂度小，但是**在最坏情况下**，二分查找需要$2Log_2n + 1$次比较，而三分查找需要$4Log_3n + 1$次比较


#### 三分查找原始抽象方法实现如下：

特点：

- 类似二分查找，将查找数组划分为三个部分来进行查找；
- 时间复杂度： $Log_3n$.

```java
// 递归实现.
public static int ternarySearch(Comparable[] a, Comparable key, int lo, int hi) {
	if (lo <= hi) {
    	int mid1 = lo + (hi - lo) / 3;
        int mid2 = mid1 + (hi - lo) / 3;
        
        if (a[mid1].compareTo(key) == 0)
        	return mid1;
        if (a[mid2].compareTo(key) == 0)
			return mid2;
        // 目标元素只可能出现在第一部分.
        if (a[mid1].compareTo(key) > 0)
			return ternarySearch(a, key, lo, mid1-1);
        // 目标元素只可能出现在第三部分.
        if (a[mid2].compareTo(key) < 0)
        	return ternarySearch(a, key, mid2+1, hi);
        // 目标元素只可能出现在第二部分.
        return ternarySearch(a, key, mid1+1, mid2-1);
    }
    // 查找失败.
    return -1;
}
```

### 指数搜索 - exponential search -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#基本查找算法---basical-searching-algorithms)

特点：

- 找到目标元素可能出现的区间；
- 使用二分查找在区间上查找目标元素;
- 时间复杂度： $logn$;

**[notice]:

1. 适用于目标数组元素大小趋向于无限大的情况；
2. 当目标出现在目标数组左边时，指数搜索速度比二分查找快。


```java
public static int exponentialSearch(Comparable[] a, Comparable key) {
	int n  = a.length;
    int i = 1;
    while (i < n && a[i].compareTo(key) < 0) {
 		i = i * 2;   	
    }
    return binarySearch(a, key, i/2, Math.min(i, n-1));
}
```

### 插值搜索 - interpolation search -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md#基本查找算法---basical-searching-algorithms)

特点：

- 原理： 假设查找数组的元素是均匀分布的，使用**直线插值搜索**，不像二分查找那样每次都是搜索区间中间元素。它能够快速的接近目标元素。

- 当元素均匀分布的情况，时间复杂度为: $O(log log n))$, 最坏情况: $O(n)$.

```java
// java program to implement interpolatoin search.
public static int interpolationSearch(Comparable[] a, Comparable key) {
	int lo = 0;
    int hi = a.length - 1;
    
    while (lo <= hi && less(key, a[hi]) && less(a[lo], key)) {
    	int pos = lo + (hi - lo) * (key - a[lo]) / (a[hi] - a[lo]);
        if (a[pos].compareTo(key) == 0)
        	return pos;
        else if (a[pos].compareTo(key) > 0)
        	hi = pos - 1;
        else 
            lo = pos + 1;
    }
    return -1;
}
```
