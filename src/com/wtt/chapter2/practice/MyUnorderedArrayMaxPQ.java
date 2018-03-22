package com.wtt.chapter2.practice;

/**
 * 2018/3/22 10:43 add by wutaotao
 */
public class MyUnorderedArrayMaxPQ<Key extends Comparable<Key>> {

    private Key[] unorderedArray;
    private int n;

    public MyUnorderedArrayMaxPQ(int max) {
        unorderedArray = (Key[])new Comparable[max];
        this.n = 0;
    }

    public boolean isEmpty() {return n == 0;}
    public int size() {return n;}
    public Key max() {return unorderedArray[n - 1];}

    public void insert(Key key) {

        if (n == unorderedArray.length) throw new UnsupportedOperationException("array overflow");
        unorderedArray[n++] = key;
    }
    public Key delMax() {

        if (n == 0) throw new UnsupportedOperationException("array underflow");
        int max = 0;
        for (int i = 1; i < n; i++) {

            if (less(unorderedArray[max], unorderedArray[i])) max = i;
        }
        exch(unorderedArray, max, n - 1);
        Key key = unorderedArray[--n];
        unorderedArray[n] = null;
        return key;
    }
    public void exch(Key[] arr, int i, int j) {
        Key tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        MyUnorderedArrayMaxPQ<Integer> pq = new MyUnorderedArrayMaxPQ<>(10);
        pq.insert(4);
        pq.insert(5);
        pq.insert(8);
        pq.insert(7);
        pq.insert(6);
        pq.insert(1);
        while(pq.size() > 0) {
            System.out.print(pq.delMax() + ",");
        }
        System.out.println("over");
    }
}
