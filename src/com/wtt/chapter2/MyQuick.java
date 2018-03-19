package com.wtt.chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * Created by wutaotao
 * 2018/3/19 21:12
 */
public class MyQuick {


    public static void sort(Comparable[] arr) {

        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    /**
     * @param arr
     * @param lo
     * @param hi
     */
    public static void sort(Comparable[] arr, int lo, int hi) {

        // always remember the recursive call has an way to get out
        if (lo >= hi) return;
        int j = cut(arr, lo, hi);
        // a[j] is set, the left side is lower level, the right side is higher level
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }
    public static int cut(Comparable[] arr, int lo, int hi) {

        Comparable val = arr[lo];
        int i = lo, j = hi + 1;

        while(true) {

            while (less(arr[++i], val)) if (i == hi) break;
            while (less(val, arr[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }
    public static void exch(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length ; i++) {
            if (less(arr[i], arr[i - 1])) return false;
        }
        return true;
    }
    public static void main(String[] args) {

        Stopwatch stopwatch = new Stopwatch();
//        Integer[] test = new Integer[]{8, 2, 0, 6, 9, 8, 7, 9, 5, 4};
        int n = 10000;
        Integer[] test = new Integer[n];
        for (int i = 0; i < n; i++) {
            test[i] = StdRandom.uniform(n);
        }
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin test:");
        MyQuick.sort(test);
        System.out.println("after test:");
        System.out.println(MyQuick.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
