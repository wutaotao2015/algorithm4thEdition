package com.wtt.chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 针对归并排序进行优化
 * 1. 小数组不用归并，用插入排序更快
 * 2. 左右两边都排好序后，若arr[mid] < arr[mid + 1],说明整个数组已经有序，可以跳过merge方法
 * 3. 复制元素？
 * Created by wutaotao
 * 2018/3/18 11:03
 */
public class MyMergeX {

    public static void sort(Comparable[] arr) {
        //auxiliary array
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    public static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {

        if (hi - lo <= 7) {
            MyInsertion.sort(arr, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        if (!less(arr[mid], arr[mid + 1])) {
            merge(arr, aux, lo, mid, hi);
        }else{
            for (int i = lo; i <= hi; i++) {
                arr[i] = aux[i];
            }
        }
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
            } else if (less(arr[j], arr[i])) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        Integer[] test = new Integer[]{8, 2, 0, 6, 9, 8, 7, 9, 5, 4};
//        int n = 100;
//        Integer[] test = new Integer[n];
//        for (int i = 0; i < n; i++) {
//            test[i] = StdRandom.uniform(n);
//        }
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin test:");
        MyMergeX.sort(test);
        System.out.println("after test:");
        MySelection.show(test);
        System.out.println(MySelection.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
