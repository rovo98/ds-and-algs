---
author: rovo98
description: datastructures and algorithms learing with java implement.
---

# Datastructures and Algorithms.

## Table of contents

- [Bag](https://github.com/rovo98/ds-and-algs#bag)
- [List](https://github.com/rovo98/ds-and-algs#list)
    - [DoubleList](https://github.com/rovo98/ds-and-algs#doublelist)
- [Stack](https://github.com/rovo98/ds-and-algs#stack)
- [Queue](https://github.com/rovo98/ds-and-algs#queue)
    - [Deque](https://github.com/rovo98/ds-and-algs#deque)
    - [Steque](https://github.com/rovo98/ds-and-algs#steque)
    - [Priority Queue](https://github.com/rovo98/ds-and-algs#priority-queue)
- [UF](https://github.com/rovo98/ds-and-algs#uf)
- [Searching Algorithms](https://github.com/rovo98/ds-and-algs#searching-algorithms)
    - [Basical Searching Algorithms](https://github.com/rovo98/ds-and-algs#basical-searching-algorithms)
- [Sorting Algorithms](https://github.com/rovo98/ds-and-algs#sorting-algorithms)
    - [Basical Sorting Algorithms](https://github.com/rovo98/ds-and-algs#basical-sorting-algorithms)
    - [Some Improvement](https://github.com/rovo98/ds-and-algs#some-improvement)

## Bag

### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/bag/Bag.java)

|Operation     |Description|
|:-------------:|:---------:|
|Bag()|Initializes an empty bag.|
|boolean isEmpty()|Returns true if bag is empty, otherwise false|
|int size()|Returns the number of items in bag.|
|void add(Item item)|Add an item into bag.|

### Implements

|Name|Description|
|:---:|:---------:|
|[ArrayBag](https://github.com/rovo98/ds-and-algs/blob/master/ds/bag/ArrayBag.java)|An implement of Bag using ResizingArray.|
|[LinkedBag](https://github.com/rovo98/ds-and-algs/blob/master/ds/bag/LinkedBag.java)|An implement of Bag using linked-list.|

## List

### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/list/datastructures/List.java)

|Operation|Description|
|:--------:|:---------:|
|List()|Initializes an empty list.|
|boolean isEmpty()|Returns true if list is empty, otherwise false.|
|void clearList()|Clear up the list.|
|Item getElem(int index)|Get the item in list at index.|
|int locateElem(Item e)|Returns the index of e if it presents in list.|
|void insertElem(int index, Item e)|Insert an item into list before index.|
|Item deleteElem(int index)|Delete and return the item at index|
|int size()|Returns the number of items in list.|

### Implements

|Name|Description|
|:---:|:-------:|
|[ArrayList](https://github.com/rovo98/ds-and-algs/blob/master/ds/list/datastructures/ArrayList.java)|An implement of List using ResizingArray.|
|[LinkedList](https://github.com/rovo98/ds-and-algs/blob/master/ds/list/datastructures/LinkedList.java)|An implement of List using single-linked-list.|

### DoubleList

#### API :

|Operation|Description|
|:-------:|:---------:|
|DoubleNode()|Initializes an empty DoubleList.|
|void addHead(Item item)|Add an item to the head of the double list.|
|void addTail(Item item)|Add an item to the tail of the double list.|
|Item deleteHead()|Delete and return the item at the head of list.|
|Item deleteTail()|Delete and return the item at the tail of the list.|
|void insertAfter(int index, Item item)|Insert an item into after index.|
|void insertBefore(int index, Item item)|Insert an item into list before index.|
|Item deleteAt(int index)|Remove the item at index.|

#### Implements

|Name|Description|
|:---:|:--------:|
|[DoubleNode](https://github.com/rovo98/ds-and-algs/blob/master/ds/list/exercises/DoubleNode.java)|An implement of DoubleList.|

## Stack

### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/stack/datastructures/Stack.java)

|Operation|Description|
|:-------:|:----------:|
|Stack()|Initializes an empty stack.|
|boolean isEmpty()|Returns true if stack is empty, otherwise false.|
|void push(Item item)|Add an item to stack.|
|Item pop()|Remove an item from stack.|
|int size()|Returns the number of items in stack.|
|Item peek()|Get an item from stack.|

### Implements

|Name|Description|
|:---:|:----------:|
|[ArrayStack](https://github.com/rovo98/ds-and-algs/blob/master/ds/stack/datastructures/ArrayStack.java)|An implement of Stack using ResizingArray.|
|[LinkedStack](https://github.com/rovo98/ds-and-algs/blob/master/ds/stack/datastructures/LinkedStack.java)|An implement of Stack using single-linked-list.|

## Queue

### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/datastructures/Queue.java)

|Operation|Description|
|:--------:|:--------:|
|Queue()|Initializes an empty queue.|
|boolean isEmpty()|Returns true if queue is empty, otherwise false.|
|int size()|Returns the number of items in queue.|
|void enqueue(Item item)|Add an item to queue.|
|Item dequeue()|Remove an item from queue.|
|Item peek()|Get the top item from the queue.|

### Implements

|Name|Description|
|:---:|:---------:|
|[LinkedQueue](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/datastructures/LinkedQueue.java)|An implement of Queue using single-linked-list.|

### Deque

#### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/Deque.java)

|Operation|Description|
|:-------:|:---------:|
|Deque()|Initailizes an empty Deque.|
|boolean isEmpty()|Returns true if deque is empty, otherwise false.|
|int size()|Returns the number of items in deque.|
|void pushLeft(Item item)|Add an item into the left side of deque.|
|void pushRight(Item item)|Add an item into the right side of deque.|
|Item popLeft()|Remove an item from the left side of the deque.|
|Item popRight()|Remove an item from the right side of the deque.|

#### Implements

|Name|Description|
|:---:|:---------:|
|[LinkedDeque](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/LinkedDeque.java)|An implement of Deque using double-linked-list.|

### Steque

#### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/Steque.java)

|Operation|Description|
|:-------:|:---------:|
|Steque()|Initializes an empty Steque.|
|boolean isEmpty()|Returns true if steque is empty, otherwise false.|
|int size()|Returns the number of items in steque.|
|void push(Item item)|push an item into steque.|
|enqueue(Item item)|enqueue(Item item) enqueue an item into steque.|
|Item pop()|Remove an item from steque.|

#### Implements

|Name|Description|
|:----:|:--------:|
|[LinkedSteque](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/LinkedSteque.java)|An implement of Steque using single-linked-list.|

### Priority Queue

#### basical implements - using ResizingArray and linked-list.

##### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/priority-queue/MaxPQ.java) (MaxPQ or MinPQ).

|Operation|Description|
|:-------:|:---------:|
|MaxPQ()|Initializes an empty maxPQ.|
|MaxPQ(Key[] a)|Initializes a maxPQ containing all keys from array a.|
|void insert(Key v)|Add a key into maxPQ.|
|Key max()|Get the maximum key from the maxPQ.|
|Key delMax()|Delete and return the maximum key from the maxPQ.|
|boolean isEmpty()|Returns true if maxPQ is empty.|
|int size()|Returns the number of keys in maxPQ.|

##### Implements

|Name|Description|
|:---:|:--------:|
|[ArrayPQWithNoOrder](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/priority-queue/ArrayPQWithNoOrder.java)|An implement of MaxPQ using ResizingArray which doesn't requires its keys to be sorted.|
|[ArrayPQWithOrder](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/priority-queue/ArrayPQWithOrder.java)|An implement of MaxPQ using ResizingArray which requires its keys to be sorted.|
|[LinkedPQWithNoOrder](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/priority-queue/LinkedPQWithNoOrder.java)|An implement of MaxPQ using single-linked-list which doesn't requires its keys to be sorted.|
|[LinkedPQWithOrder](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/priority-queue/LinkedPQWithOrder.java)|An implement of MaxPQ using single-linked-list which requires its keys to be sorted.|

## UF

### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/datastructures/UF_API.java)

|Operation|Description|
|:-------:|:---------:|
|UF(int n)|Initializes an empty union-find data structure with n sites.|
|int find(int p)|Returns the component identifier for the component containing site p.|
|boolean connected(int p, int q)|Returns true if two sites are in the same component.|
|void union(int p, int q)|Merges the component containing site p with the componnet containing site q.|
|int count()|Returns the number of the components.|

### Implements

|Name|Description|
|:---:|:---------:|
|[UF](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/datastructures/UF.java)|An implement of UF using height of tree for ranking and path halving.|
|[QuickFindUF](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/exercises/QuickFindUF.java)|An implement of UF having quick find method.|
|[QuickUnionUF](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/exercises/QuickUnionUF.java)|An implement of UF having quick union method.|
|[WeightedUF](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/exercises/WeightedQuickUnion.java)|An implement of UF using number of nodes of tree for ranking and path halving.|

## Searching Algorithms

### Basical Searching Algorithms

|Name|Time complexity|Space complexity|
|:---:|:------------:|:--------------:|
|[LinearSearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/LinearSearch.java)|O(n)|O(1)|
|[JumpSearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/JumpSearch.java)|O(\sqrt{n})|O(1)|
|[BinarySearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/BinarySearch.java)|O(log n)|O(1)|
|[ExponentialSearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/ExponentialSearch.java)|O(log n)|O(1)|
|[InterpolationSearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/InterpolationSearch.java)|bc: O(log log n),wc: O(n)|O(1)|
|[TernarySearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/TernarySearch.java)|O(log_3 n)|O(1)|

## Sorting Algorithms

### Basical Sorting Algorithms

|Name|Time complexity|Space complexity|Stablility|
|:---:|:------------:|:---------------:|:--------:|
|[BubbleSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Bubble.java)|O(n^2)|O(1)|stable|
|[InsertionSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Insertion.java)|O(n^2)|O(1)|stable|
|[SelectionSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Selection.java)|O(n^2)|O(1)|unstable|
|[ShellSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Shell.java)|approximate to O(n log n)|O(1)|unstable|
|[MergeSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Merge.java)|O(n log n)|O(n)|stable|
|[QuickSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Quick.java)|O(n log n)|O(1)|unstable|

### Some Improvements

|Name|Description|
|:---:|:-----------:|
|[MergeSortImproved](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/MergeSortImproved.java)|Using insertionSort to sort the small subarrays and check if subarray is sorted already.|
|[QuickMerge](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/QuickMerge.java)|An approach to speed up merging subarrays, but it's unstable.|
|[Merge3Ways](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/Merge3Ways.java)|MergeSort the unsorted array by dividing it into three parts and merging them recursively.|
|[QuickSortImproved](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/QuickSortImproved.java)|Using insertionSort to sort the small subarrays.|
|[QuickSortImprovedTwo](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/QuickSortImprovedTwo.java)|Sampling three points for picking a pivot.|
|[Quick3Ways](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/Quick3Ways.java)|QuickSort the unsorted array by dividing it into three halves. One's elements all less than pivot, second one equals to pivot and the last greater than pivot.|

