package com.wtt.chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * Created by wutaotao
 * 2018/3/18 21:45
 */
public class MyMergeBU {

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        Comparable[] aux = new Comparable[n];

        for(int size = 1; size < n; size = size + size) {

            for (int lo = 0; lo < n - size; lo = lo + size + size) {

                merge(arr, aux, lo, lo + size - 1, Math.min(lo + size + size - 1, n - 1));
            }
        }
    }
    public static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if(less(aux[j], aux[i])) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
        int n = 10000;
        Integer[] test = new Integer[n];
        for (int i = 0; i < n; i++) {
            test[i] = StdRandom.uniform(n);
        }
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin test:");
        MyMergeBU.sort(test);
        System.out.println("after test:");
        System.out.println(Arrays.toString(test));
        System.out.println(MyMergeBU.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
