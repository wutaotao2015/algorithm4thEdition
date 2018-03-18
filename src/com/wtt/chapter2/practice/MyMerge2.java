package com.wtt.chapter2.practice;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * Created by wutaotao
 * 2018/3/18 20:15
 */
public class MyMerge2 {

    public static void sort(Comparable[] arr) {

        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    public static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {

        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);

        assert isSorted(arr, lo, mid);
        assert isSorted(arr, mid + 1, hi);

        merge(arr, aux, lo, mid, hi);

        assert isSorted(arr, lo, hi);
    }

    // 从arr拷贝到aux中，在aux中比较后归并到arr中，可以利用对称性省略掉拷贝元素这一步。
    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    public static boolean isSorted(Comparable[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(arr[i], arr[i - 1])) return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) return false;
        }
        return true;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
        MyMerge2.sort(test);
        System.out.println("after test:");
        System.out.println(Arrays.toString(test));
        System.out.println(MyMerge2.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
