package com.wtt.chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 相比于普通的快速排序的二分法，
 * 当输入数组的元素有重复主键出现时，利用三向快速排序法，
 * 可以将NlogN的复杂度逼近到线性N的程度。
 * 2018/3/21 13:32 add by wutaotao
 */
public class MyQuick3Way {

    public static void sortTwo(Comparable[] arr) {

        int lt = 0, i = 1, gt = arr.length - 1;
        Comparable v = arr[0];
        while(i <= gt) {
            int comp = arr[i].compareTo(v);
            if (comp < 0) {
                exch(arr, i++, lt++);
            } else if (comp > 0) {
                exch(arr, i, gt--);
            } else {
                i++;
            }
        }
    }
    public static void sort(Comparable[] arr) {

        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(Comparable[] arr, int lo, int hi) {

        if (lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = arr[lo];
        while (i <= gt) {

            if (less(arr[i], v)) {
                exch(arr, i++, lt++);
            } else if (less(v, arr[i])) {
                exch(arr, i, gt--);
            } else {
                i++;
            }
        }
        sort(arr, lo, lt - 1);
        sort(arr, gt + 1, hi);
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {

            if (less(arr[i], arr[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
//        Integer[] test = new Integer[]{8, 2, 0, 6, 9, 8, 7, 9, 5, 4};
        Integer[] test = new Integer[]{2,1,2,1,2,1,1,2,2};
//        int n = 10000;
//        Integer[] test = new Integer[n];
//        for (int i = 0; i < n; i++) {
//            test[i] = StdRandom.uniform(n);
//        }
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin test:");
        MyQuick3Way.sortTwo(test);
        System.out.println("after test:");
        System.out.println(Arrays.toString(test));
        System.out.println(MyQuick3Way.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}













