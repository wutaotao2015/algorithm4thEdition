package com.wtt.chapter3;

import edu.princeton.cs.algs4.Queue;

/**
 * get lgN
 * put  N
 * Created by wutaotao
 * 2018/3/26 20:54
 */
public class MyBinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] values;
    private int n;

    public MyBinarySearchST(int max) {

        keys = (Key[]) new Comparable[max];
        values = (Value[]) new Object[max];
        this.n = 0;
    }

    public void put(Key key, Value value) {

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            values[i] = value;
        } else {
            // here can resize
            for (int j = n; j > i; j--) {
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }
            keys[i] = key;
            values[i] = value;
            n++;
        }
    }

    public Value get(Key key) {

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    public int rank(Key key) {

        int lo = 0, hi = n - 1;
        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2;
            int res = keys[mid].compareTo(key);
            if (res > 0) {
                hi = mid - 1;
            } else if (res < 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public int size() {
        return n;
    }

    public void delete(Key key) {

        if (key == null) throw new NullPointerException("key is null");
        if (n == 0) return;

        int i = rank(key);
        if (i == n || keys[i].compareTo(key) != 0) return;
//        for (int j = n - 2; j >= i; j--) {
//            keys[j] = keys[j + 1];
//            values[j] = values[j + 1];
//        }
//        keys[n - 1] = null;
//        values[n - 1] = null;
//        n--;
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        n--;
        keys[n] = null;
        values[n] = null;
        // here can resize
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[n - 1];
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    // because of the rank function return lo is mid + 1,
    // so the ceiling is keys[i], the floor is keys[i - 1]
    public Key floor(Key key) {
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];
    }
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == n) return null;
        else return keys[i];
    }
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    public static void main(String[] args) {
        MyBinarySearchST<Integer, String> binarySearchST = new MyBinarySearchST<Integer, String>(10);
        binarySearchST.put(12, "sll");
        binarySearchST.put(2, "wsl");
        binarySearchST.put(5, "wtt");
        System.out.println(binarySearchST.size());
        System.out.println(binarySearchST.get(2));
        binarySearchST.put(2, "xlx");
        System.out.println(binarySearchST.get(2));
        Iterable<Integer> keys = binarySearchST.keys();
        for (Integer key : keys) {
            System.out.print(key + ",");
        }
        System.out.println();
        binarySearchST.delete(2);
        System.out.println(binarySearchST.size());
        System.out.println(binarySearchST.get(2));
        keys = binarySearchST.keys();
        for (Integer key : keys) {
            System.out.print(key + ",");
        }
        System.out.println();
    }
}
