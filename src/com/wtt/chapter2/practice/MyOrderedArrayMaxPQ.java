package com.wtt.chapter2.practice;


/**
 * 2018/3/22 9:56 add by wutaotao
 */
public class MyOrderedArrayMaxPQ<Key extends Comparable<Key>> {

    private Key[] orderedArray;
    private int n;

    public MyOrderedArrayMaxPQ() {
        // 可以这样写。。。。。
        orderedArray = (Key[]) new Comparable[10];
    }

    public MyOrderedArrayMaxPQ(int max) {
        orderedArray = (Key[]) new Comparable[max];
        n = 0;
    }

    public MyOrderedArrayMaxPQ(Key[] orderedArray) {
        this.orderedArray = orderedArray;
        n = orderedArray.length;
    }

    // 通过插入排序的方法使得栈顶的元素orderedArray[n - 1]是最大的元素, 升序排列
    public void insert(Key key) {
        if (n == orderedArray.length) throw new UnsupportedOperationException("array overflow");
        n++;
        int j;
        for (j = n - 1; j > 0 && less(key, orderedArray[j - 1]); j--) {
            orderedArray[j] = orderedArray[j - 1];
        }
        orderedArray[j] = key;
    }

    public Key max() {
        return orderedArray[n - 1];
    }

    public boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }
    public Key delMax() {
        if (n == 0) throw new UnsupportedOperationException("array underflow");
        Key key = orderedArray[--n];
        orderedArray[n] = null;
        return key;
    }

    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }

    public static void main(String[] args) {

        MyOrderedArrayMaxPQ<Integer> orderedArrayMaxPQ = new MyOrderedArrayMaxPQ<>();
        orderedArrayMaxPQ.insert(5);
        orderedArrayMaxPQ.insert(1);
        orderedArrayMaxPQ.insert(3);
        orderedArrayMaxPQ.insert(2);
        orderedArrayMaxPQ.insert(8);
        orderedArrayMaxPQ.insert(4);
        while(orderedArrayMaxPQ.size() > 0) {
            System.out.print(orderedArrayMaxPQ.delMax() + ",");
        }
    }
}















