package com.wtt.chapter2;

/**
 * 2018/3/22 16:39 add by wutaotao
 */
public class MyMaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n = 0;

    public MyMaxPQ(int max) {
        // 第一个元素pq[0]没有使用，故加1，这样n和元素索引就是相同的
        pq = (Key[]) new Comparable[max + 1];
    }

    public void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {

            int j = 2 * k;
            while (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    // insert to the bottom, then swim
    public void insert(Key key) {
        pq[++n] = key;
        swim(n);
    }
    // exchange pq[1] with the pq[n],pop pq[1],and sink the current pq[1]
    public Key delMax() {
        Key max = pq[1];
        exch(1, n--);
        pq[n+1] = null;
        sink(1);
        return max;
    }

    public Key max() {
        return pq[1];
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }

    public static void main(String[] args) {
        MyMaxPQ<Integer> pq = new MyMaxPQ<Integer>(10);
        pq.insert(5);
        pq.insert(6);
        pq.insert(9);
        pq.insert(11);
        pq.insert(0);
        pq.insert(4);
        pq.insert(1);
        while (pq.size() > 0) {
            System.out.print(pq.delMax() + ",");
        }
        System.out.println();
        System.out.println("over");
    }
}
