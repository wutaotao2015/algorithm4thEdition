package com.wtt.chapter2.practice;

import java.util.NoSuchElementException;

/**
 * 2018/3/23 14:55 add by wutaotao
 */
public class MyIndexMinPQ<Key extends Comparable<Key>> {

    // the max number of elements of keys
    private int maxN;
    // the current number of elements
    private int n;

    // the elements to be handled, keys[0] is comparable to keys[1]
    private Key[] keys;

    // the indexes of the items, when the items is inserted,the item is given with a index,
    // the ordered indexes of pq indicate the keys priorities,then you can get the min priority item
    // from the index pq[1]
    private int[] pq;

    //判断索引4是否在优先队列中：
    //1.普通方法：
    //a[i] == 4?
    //a
    // 0 1 2
    //[3,4,5]
    //
    //2. 颠倒数组a中位置和值的关系：
    //b[4] == -1?
    //b
    //  0  1  2 3 4 5
    //[-1,-1,-1,0,1,2]  value is the location of a
    // reverse of pq  pq[1] = minPriorityIndex,then qp[mpIndex] = 1
    // find the location of some index in pq, you can find if the index is in the pq
    private int[] qp;

    public MyIndexMinPQ(int maxN) {
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        // pq[0] will not be used
        pq = new int[maxN + 1];
        // qp[maxN] will not be used, because the index is between 0 and maxN-1
        qp = new int[maxN + 1];
        for (int i = 0; i < maxN + 1; i++) {
            // this means all the indexes(0->maxN-1) are not used yet
            qp[i] = -1;
        }
    }

    //insert one item with a index
    public void insert(int i, Key key) {
        // i is 0 to (maxN-1)
        if (i < 0 || i >= maxN) throw new UnsupportedOperationException("queue overflow");
        if (contains(i)) throw new UnsupportedOperationException("index already existed");
        n++;
        // i's location is n
        qp[i] = n;
        // n location's index is i
        pq[n] = i;
        // put the item into key[] according to the index i
        keys[i] = key;
        // reheapifying
        swim(n);
    }

    public boolean contains(int i) {
//        for (int index : pq) {
//            if (index == i) return true;
//        }
//        return false;
        return qp[i] != -1;
    }

    // k is the keys location
    public void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {

            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * @param i the location of key
     * @param j another location of key
     * @return
     */
    public boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    /**
     * 由此方法可以看出，数组中2个元素互相交换，可以看成2个元素的值交换，
     * 也可以看成元素值不动，交换位置，
     * 放到逆序数组中，也变成了交换元素值
     * @param i index location -> n
     * @param j index location -> n
     */
    public void exch(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
        // 原来是pq[i] = indexI,pq交换后变为pq[i] = indexJ
        // 原来是qp[indexI] = i,现在需要变为qp[indexI] = j, 在qp中，indexI是交换前的tmp,即经过交换后的pq[j]
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    public int delMin() {
        if (n == 0) throw new UnsupportedOperationException("queue underflow");
        int min = pq[1];
        exch(1,n--);
        sink(1);
        keys[min] = null;
        qp[min] = -1;
        pq[n+1] = -1;
        return min;
    }
    public void sink(int k) {
        while (2*k <= n) {
            int j = 2 * k;
            while(j < n && greater(j, j+1)) j++;
            exch(k, j);
            k = j;
        }
    }
    public void delete(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }
}
