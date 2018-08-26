---
author: rovo98
description: learning from algs4
---
# union-find -- 动态连通性问题算法

## Table of contents

- [引入](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#引入)
- [Union Find API](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#union-find-api----go-back-to-top)
- [API的基本方法实现](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#实现----go-back-to-top)
- Find 和 Union 的不同实现：
	- [quick-find算法](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#第一个实现-quick-find算法----go-back-to-top)
	- [quick-union算法](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#第二种实现-quick-union算法----go-back-to-top)
	- [加权quick-union算法](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#第三种实现-加权quick-union算法----go-back-to-top)


### 引入
我们设计算法时面对的第一个任务就是**精确地定义问题**。为了说明问题，通常会设计一份*API*来封装所需的基本操作。

### union-find API -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#union-find----动态连通性问题算法)
|         public class UF         |                     |
| :-----------------------------: | :-----------------: |
|            UF(int n)            | 以整数标识(0到n-1)初始化n个触点 |
|         int find(int p)         |      p所在分量的标识符      |
|    void union(int p, int q)     |    在p和q之间增加一条连接     |
| boolean connected(int p, int q) | 如果p和q存在于同一个分量返回true |
|           int count()           |       连通分支的数量       |
此时，解决动态连通性问题设计算法的问题已经被我们转化为实现这份API。

- 定义一种数据结构表示已知的连接
- 基于此数据结构实现高效的union()、find()、connected()和count()方法

### 实现 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#union-find----动态连通性问题算法)
```java
public class UF {
	private int[] parent;
    private int count;
    
    public UF(int n) {
    	parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
        	parent[i] = i;
        }
    }
    public int count() {
    	return count;
    }
    public boolean connected(int p, int q) {
    	return find(p) == find(q);
    }
    public int find(int p) ...
    public void union(int p, int q) ...
}
```
#### 第一个实现: quick-find算法 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#union-find----动态连通性问题算法)

   一种方法保证当且仅当```parent[p] == parent[q]```时p和q是连通的。即在同一个连通分支的所有触点在```parent[]```中的值必须全部相同。 也意味着```connected(p, q)```只需要判断```parent[p] == parent[q]```，只有在p和q所在连通分支相同时返回true;否则p所在连通分支的所有触点对应```parent[]```中的值为一个值，而q所在连通分支的所有触点对应```parent[]```中的值为另一个值。因此我们在合并分量时需要**遍历整个数组**来将所有和```parent[p]```相等的元素变为```parent[q]```，或者反过来。
由此实现的```find()、union()```方法如下：
```java
public int find(int p) {
	return parent[p];
}
public void union(int p, int q) {
	int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) return;
    for (int i = 0; i < parent.length; i++) {
    	if (parent[i] == rootQ) parent[i] = rootP;
    }
    count--;
}
```

#### quick-find 算法分析

find() 操作速度显然是很快的，因为它只需要访问```parent[]```数组一次。但quick-find算法一般无法处理大型问题，因为对于每一对输入的触点 ```union()```都需要扫描整个```parent[]```数组。可以看出此算法的时间复杂度应该为：$O(n^2).$

命题F: 在quick-find 算法中，每次find() 调用只需要访问数组一次，而归并两个分量的union()操作访问数组的次数在(N+3)到(2N+1)之间。

#### 第二种实现： quick-union算法 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#union-find----动态连通性问题算法)

该算法的重点是在于提高 ```union()```方法的速度，它和 quick-find 都是基于相同的数据结构 —— 以触点为索引的 ```parent[]``` 数组，在此之上，我们用它们来定义更加复杂的结构。令每个触点所对应的 ```parent[]``` 元素都是同一个分量中的另一触点名称（也可能是它自己) -- 这种联系称为**链接**。

在实现```find()```方法时，从给定的触点开始，由它的链接得到另一个触点，再由这个触点的链接到达第三个触点，直到随着链接到达**根触点，链接指向自己的触点**。
而对于实现```union()```方法，只需有由p和q的链接分别去找它们的根触点，然后只需要将一个跟触点链接到另一个即可。由此实现的```find()、union()```方法如下：
```java
public int find(int p) {
	while (p != parent[p]) {
    	p = parent[p];
    }
    return p;
}
public void union(int p, int q) {
	int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) return;
    parent[rootP] = parent[rootQ];
    count--;
}
```
#### quick-union 算法分析 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#union-find----动态连通性问题算法)
在quick-union 中```parent[]```数组用父链接的形式表示的一片森林。
quick-union 算法明显比quick-find 算法更快，因为它不需要为每一对输入遍历整个数组。

在最好的情况下，```find()```只需要访问一次数组就能得到一个触点所在的分量的标识符；而在最坏的情况下，需要$2N - 1$次数组访问。
**我们可以把quick-union看作是quick-find算法的改进**。—— 它将```union()```操作改进为线性级别。

```txt
定义： 一棵树的大小是它的节点的数量。树中的一个节点
的深度是它到根节点的路径的链接数。树的高度是它的所有节点的最大深度。

命题G: quick-union 算法中的 find() 方法访问数组的
次数为1 加上给定触点所对应的节点的深度的两倍。
union() 和 connected() 访问数组的次数为两次 find()
操作 （如果 union() 中给定的两个触点分别存在于不同的树中则还需要加 1).
```
**由命题G我们可以知道算法在最坏的情况下的运行时间是平方级别的。**
例如：输入的整数对为0-1、0-2、0-3等，N-1 对之后，N 个触点将全部处于相同的集合之中且由quick-union算法得到的树的高度为 N-1, 其中0链接到2, 2链接到3，如此下去。由命题G可知，对于整数对 $0 i$, union() 操作访问数组的次数为$2i + 2$ （触点0的深度为i, 触点 i 的深度为 0）。 处理 N 对整数所需的所有 find() 操作访问数组的总次数为 $2(1+2+...+N)~N^2$。

#### 第三种实现： 加权quick-union算法 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#union-find----动态连通性问题算法)
改进quick-union算法，不再随意在```union()```中将一棵树连接到另一颗树，而是**记录树的大小(节点个数)或高度并总是将较小的树连接到较大的树上**。

- 记录树的大小(节点个数)加权

```java
public class UF {
	private int[] parent;
    private int[] rank;
    private count;
    public UF(int n) {
    	parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
        	parent[i] = i;
            rank[i] = 1;
        }
    }
    public int find(int p) {
    	while (p != parent[p]) {
        	parent[p] = parent[parent[p]]; // 路径压缩
            p = parent[p];
        }
        return p;
    }
    public void union(int p, int q) {
    	int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        // 将较小的树连接到较大的树上
        if (rank[rootP] > rank[rootQ]) {
        	parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        } else {
        	parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        }
        count--;
    }
}
```

- 记录树的高度

```java
public class UF {
	private int[] parent;
    private byte[] rank;
    private count;
    public UF(int n) {
    	parent = new int[n];
        rank = new byte[n];
        count = n;
        for (int i = 0; i < n; i++) {
        	parent[i] = i;
            rank[i] = 0;
        }
    }
    public int find(int p) {
    	while (p != parent[p]) {
        	parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    public void union(int p, int q) {
    	int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        // 将高度较低的树连接到高度较高的树上
        if (rank[rootP] > rank[rootQ]) {
        	parent[rootQ] = rootP;
        }
        else if (rank[rootP] < rank[rootQ]) {
        	parent[rootP] = rootQ;
        }
        else {
        	parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count++;
    }
}
```

#### 加权quick-union 算法分析 -- [go back to top](https://github.com/rovo98/ds-and-algs/blob/master/notes/uf/union-find.md#union-find----动态连通性问题算法)

```txt
命题H: 对于 N 个触点，加权 quick-union 算法构造的
森林中的任意节点的深度最多为 lgN。

推论: 对于加权 quick-union 算法和 N 个触点， 在最
坏的情况下 find()、 connected() 和 union() 的成本
的增长数量级为 log N。
```

**union-find 的详细实现: [查看](https://github.com/rovo98/ds-and-algs/blob/master/ds/uf/datastructures/UF.java)**.
