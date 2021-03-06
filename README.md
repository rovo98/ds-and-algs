---
author: rovo98
description: datastructures and algorithms learing with java implement.
---

# Datastructures and Algorithms.

## Table of contents

- [Bag](https://github.com/rovo98/ds-and-algs#bag)
- [List](https://github.com/rovo98/ds-and-algs#list----go-back-to-top)
    - [DoubleList](https://github.com/rovo98/ds-and-algs#doublelist----go-back-to-top)
- [Stack](https://github.com/rovo98/ds-and-algs#stack----go-back-to-top)
- [Queue](https://github.com/rovo98/ds-and-algs#queue----go-back-to-top)
    - [Deque](https://github.com/rovo98/ds-and-algs#deque----go-back-to-top)
    - [Steque](https://github.com/rovo98/ds-and-algs#steque----go-back-to-top)
    - [Priority Queue](https://github.com/rovo98/ds-and-algs#priority-queue----go-back-to-top)
- [UF](https://github.com/rovo98/ds-and-algs#uf----go-back-to-top)
- [Symbol Table](https://github.com/rovo98/ds-and-algs#symbol-table----go-back-to-top)
    - SequentialSearchST
    - BinarySearchST
- [Searching Algorithms](https://github.com/rovo98/ds-and-algs#searching-algorithms----go-back-to-top)
    - [Basical Searching Algorithms](https://github.com/rovo98/ds-and-algs#basical-searching-algorithms---notes)
- [Sorting Algorithms](https://github.com/rovo98/ds-and-algs#sorting-algorithms----go-back-to-top)
    - [Basical Sorting Algorithms](https://github.com/rovo98/ds-and-algs#basical-sorting-algorithms---notes)
    - [Some Improvements](https://github.com/rovo98/ds-and-algs#some-improvements---notes-for-mergesort---notes-for-quicksort)


```txt
ds-and-algs
├── ds
│   ├── bag
│   │   ├── datastructures
│   │   │   ├── ArrayBag.java
│   │   │   ├── Bag.java
│   │   │   └── LinkedBag.java
│   │   └── exercises
│   │       └── RandomBag.java
│   ├── graph
│   │   └── algorithms
│   │       └── DetectCycleDemo.java
│   ├── list
│   │   ├── algorithms
│   │   │   ├── InsertionSortList.java
│   │   │   ├── MergeSortList.java
│   │   │   └── ReverseList.java
│   │   ├── datastructures
│   │   │   ├── ArrayList.java
│   │   │   ├── LinkedList.java
│   │   │   └── List.java
│   │   └── exercises
│   │       └── DoubleNode.java
│   ├── queue
│   │   ├── datastructures
│   │   │   ├── LinkedQueue.java
│   │   │   └── Queue.java
│   │   └── exercises
│   │       ├── Deque.java
│   │       ├── LinkedDeque.java
│   │       ├── LinkedSteque.java
│   │       ├── priority-queue
│   │       │   ├── ArrayPQWithNoOrder.java
│   │       │   ├── ArrayPQWithOrder.java
│   │       │   ├── exercises
│   │       │   │   ├── HeapMinPQ.java
│   │       │   │   ├── MaxPQDemo.java
│   │       │   │   └── MinPQ.java
│   │       │   ├── HeapMaxPQ.java
│   │       │   ├── LinkedPQWithNoOrder.java
│   │       │   ├── LinkedPQWithOrder.java
│   │       │   └── MaxPQ.java
│   │       ├── RandomQueue.java
│   │       ├── ResizingArrayDeque.java
│   │       └── Steque.java
│   ├── searching
│   │   ├── BinarySearch.java
│   │   ├── ExponentialSearch.java
│   │   ├── InterpolationSearch.java
│   │   ├── JumpSearch.java
│   │   ├── LinearSearch.java
│   │   └── TernarySearch.java
│   ├── sorting
│   │   ├── Bubble.java
│   │   ├── exercises
│   │   │   ├── Merge3Ways.java
│   │   │   ├── MergeSortedQueue.java
│   │   │   ├── MergeSortImproved.java
│   │   │   ├── Quick3WaysImproved.java
│   │   │   ├── Quick3Ways.java
│   │   │   ├── QuickMerge.java
│   │   │   ├── QuickSortImproved.java
│   │   │   ├── QuickSortImprovedTwo.java
│   │   │   ├── SortCompare.java
│   │   │   ├── Stopwatch.java
│   │   │   └── UBMergeSortDemo.java
│   │   ├── Heap.java
│   │   ├── Insertion.java
│   │   ├── Merge.java
│   │   ├── Quick.java
│   │   ├── Selection.java
│   │   └── Shell.java
│   ├── stack
│   │   ├── algorithms
│   │   │   └── SortAStackUsingRecursion.java
│   │   ├── datastructures
│   │   │   ├── ArrayStack.java
│   │   │   ├── FixedCapacityStack.java
│   │   │   ├── LinkedStack.java
│   │   │   └── Stack.java
│   │   └── exercises
│   │       ├── Evaluate.java
│   │       ├── Parentheses.java
│   │       ├── Parentheses.txt
│   │       ├── StackDemo1.java
│   │       ├── StackDemo2.java
│   │       ├── StackDemo2.txt
│   │       └── StackDemo3.java
│   ├── symbolTable
│   │   ├── datastructures
│   │   │   ├── BinarySearchST.java
│   │   │   ├── SequentialSearchST.java
│   │   │   └── ST.java
│   │   └── exercises
│   │       └── symbolTable_exercises
│   │           ├── ArrayST.java
│   │           ├── BinarySearchSortedST.java
│   │           ├── BinarySearchTreeST.java
│   │           ├── BST.java
│   │           ├── FrequencyCounter.java
│   │           ├── OrderedSequentialSearchST.java
│   │           ├── ScoreRankPrinter.java
│   │           ├── scoreRank.txt
│   │           ├── SortedST.java
│   │           ├── tale.txt
│   │           └── tinyTale.txt
│   └── uf
│       ├── datastructures
│       │   ├── UF_API.java
│       │   └── UF.java
│       └── exercises
│           ├── quick-find_for_wqf.txt
│           ├── QuickFindUF.java
│           ├── QuickUnionUF.java
│           ├── UF_test.txt
│           └── WeightedQuickUnion.java
├── images
│   └── sorting
│       ├── bs_insertionSort_1.png
│       ├── bs_selectionSort_1.png
│       ├── bs_shellSort_1.png
│       ├── mergeSort_buSort_1.png
│       ├── mergeSort_merge.png
│       ├── mergeSort_ubSort_1.png
│       ├── mergeSort_ubSort_2.png
│       ├── mergeSort_ubSort_3.png
│       ├── quickSort_partition_1.png
│       ├── quickSort_partition_2.png
│       └── README.md
├── notes
│   ├── searching
│   │   └── basical-searching-algorithms.md
│   ├── sorting
│   │   ├── basical-sorting-algs.md
│   │   ├── mergeSort.md
│   │   └── quickSort.md
│   └── uf
│       └── union-find.md
└── README.md

35 directories, 103 files
```

## Bag

### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/bag/datastructures/Bag.java)

|Operation     |Description|
|:-------------:|:---------:|
|Bag()|Initializes an empty bag.|
|boolean isEmpty()|Returns true if bag is empty, otherwise false|
|int size()|Returns the number of items in bag.|
|void add(Item item)|Add an item into bag.|

### Implements

|Name|Description|
|:---:|:---------:|
|[ArrayBag](https://github.com/rovo98/ds-and-algs/blob/master/ds/bag/datastructures/ArrayBag.java)|An implement of Bag using ResizingArray.|
|[LinkedBag](https://github.com/rovo98/ds-and-algs/blob/master/ds/bag/datastructures/LinkedBag.java)|An implement of Bag using linked-list.|

## List -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

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

### DoubleList -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

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

## Stack -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

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

## Queue -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

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

### Deque -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

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

### Steque -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

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

### Priority Queue -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

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

#### Implementation using heap

|Name|Description|
|:---:|:---------:|
|[HeapMaxPQ](https://github.com/rovo98/ds-and-algs/blob/master/ds/queue/exercises/priority-queue/HeapMaxPQ.java)|An implementation of MaxPQ using heap to swim or sink after doing insert or delMax operation|

#### Time complexity comparision
|Name|insert()|delMax()|max()|
|:---:|:----:|:------:|:-----:|
|ArrayPQWithNoOrder|O(1)|O(n)|O(n)|
|ArrayPQWithOrder|O(n)|O(1)|O(1)|
|LinkedPQWithNoOrder|O(1)|O(n)|O(n)|
|LinkedPQWithOrder|O(n)|O(1)|O(1)|
|HeapMaxPQ|O(log n)|O(log n)|O(1)|


## UF -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

### API : [details](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/datastructures/UF_API.java)

|Operation|Description|
|:-------:|:---------:|
|UF(int n)|Initializes an empty union-find data structure with n sites.|
|int find(int p)|Returns the component identifier for the component containing site p.|
|boolean connected(int p, int q)|Returns true if two sites are in the same component.|
|void union(int p, int q)|Merges the component containing site p with the componnet containing site q.|
|int count()|Returns the number of the components.|

### Implements - [notes](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md)

|Name|Description|
|:---:|:---------:|
|[UF](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/datastructures/UF.java)|An implement of UF using height of blob for ranking and path halving.|
|[QuickFindUF](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/exercises/QuickFindUF.java)|An implement of UF having quick find method.|
|[QuickUnionUF](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/exercises/QuickUnionUF.java)|An implement of UF having quick union method.|
|[WeightedUF](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/exercises/WeightedQuickUnion.java)|An implement of UF using number of nodes of blob for ranking and path halving.|

## Symbol Table -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

### API : [detials](https://github.com/rovo98/ds-and-algs/blob/master/ds/symbolTable/datastructures/ST.java)

|Operation|Description|
|:-------:|:----------:|
|ST()|Initializes an empty symbol table|
|void put(Key key, Value val)|Add a key-value into table, delete key if val is null|
|Value get(Key key)|Get the value of a key-value which key is key|
|void delete(Key key)|Delete a key-value|
|boolean contains(Key key)|Returns true if key in table, otherwise false|
|boolean isEmpty()|Returns true if symbol table if empty, otherwise false|
|int size()|Returns the number of keys in table|
|Iterable<Key> keys()|Returns an iterable obj that iterates over keys in table|

### Implements

|Name|Description|
|:----:|:--------:|
|[SequentialSearchST](https://github.com/rovo98/ds-and-algs/blob/master/ds/symbolTable/datastructures/SequentialSearchST.java)|An implementation of ST using singly-linked-list with no order|
|[BinarySearchST](https://github.com/rovo98/ds-and-algs/blob/master/ds/symbolTable/datastructures/BinarySearchST.java)|An implementation of ST using resizing arrays and binary search algorithm|

#### Time Complexity of these two implementations

|ds-name|worst case||average case||Is high preformance|
|:------:|:------:|:------:|:-----:|:-------:|:---------:|
|   |get()|put()|get()|put()| |
|SequantialSearchST| N | N | N/2  | N| NO |
|BinarySearchST|lgN|2N|lgN|N|Yes|

## Searching Algorithms -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

### Basical Searching Algorithms - [notes](https://github.com/rovo98/ds-and-algs/blob/master/notes/searching/basical-searching-algorithms.md)

|Name|Time complexity|Space complexity|
|:---:|:------------:|:--------------:|
|[LinearSearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/LinearSearch.java)|O(n)|O(1)|
|[JumpSearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/JumpSearch.java)|O(\sqrt{n})|O(1)|
|[BinarySearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/BinarySearch.java)|O(log n)|O(1)|
|[ExponentialSearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/ExponentialSearch.java)|O(log n)|O(1)|
|[InterpolationSearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/InterpolationSearch.java)|bc: O(log log n),wc: O(n)|O(1)|
|[TernarySearch](https://github.com/rovo98/ds-and-algs/blob/master/ds/searching/TernarySearch.java)|O(log_3 n)|O(1)|

## Sorting Algorithms -- [go back to top](https://github.com/rovo98/ds-and-algs#datastructures-and-algorithms)

### Basical Sorting Algorithms - [notes](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/basical-sorting-algs.md)

|Name|Time complexity|Space complexity|Stablility|
|:---:|:------------:|:---------------:|:--------:|
|[BubbleSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Bubble.java)|O(n^2)|O(1)|stable|
|[InsertionSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Insertion.java)|O(n^2)|O(1)|stable|
|[SelectionSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Selection.java)|O(n^2)|O(1)|unstable|
|[ShellSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Shell.java)|approximate to O(n log n)|O(1)|unstable|
|[MergeSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Merge.java)|O(n log n)|O(n)|stable|
|[QuickSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Quick.java)|O(n log n)|O(1)|unstable|
|[HeapSort](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/Heap.java)|O(n log n)|O(1)|unstable|

### Some Improvements - [notes for mergeSort](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/mergeSort.md) - [notes for quickSort](https://github.com/rovo98/ds-and-algs/blob/master/notes/sorting/quickSort.md)

|Name|Description|
|:---:|:-----------:|
|[MergeSortImproved](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/MergeSortImproved.java)|Using insertionSort to sort the small subarrays and check if subarray is sorted already.|
|[QuickMerge](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/QuickMerge.java)|An approach to speed up merging subarrays, but it's unstable.|
|[Merge3Ways](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/Merge3Ways.java)|MergeSort the unsorted array by dividing it into three parts and merging them recursively.|
|[QuickSortImproved](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/QuickSortImproved.java)|Using insertionSort to sort the small subarrays.|
|[QuickSortImprovedTwo](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/QuickSortImprovedTwo.java)|Sampling three points for picking a pivot.|
|[Quick3Ways](https://github.com/rovo98/ds-and-algs/blob/master/ds/sorting/exercises/Quick3Ways.java)|QuickSort the unsorted array by dividing it into three halves. One's elements all less than pivot, second one equals to pivot and the last greater than pivot.|

