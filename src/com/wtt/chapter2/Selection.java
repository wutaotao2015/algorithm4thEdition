package com.wtt.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 选择排序
 * 比较次数是 (N-1) + (N-2) + ... + 1 ~ N^2/2
 * 交换次数是N次，所有算法中是最少的
 * <p>
 * 在数组元素个数相同的情况下，
 * 一个完全排好序的数组和随机的数组的
 * 选择排序所花的时间完全相同，
 * 即其所花时间与输入状态无关。
 */
public class Selection {

    public static void sort(Comparable[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[min])) min = j;
            }
            exch(arr, i, min);
//            System.out.println(Arrays.toString(arr));
        }
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

    public static void show(Comparable[] arr) {
        for (Comparable a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Stopwatch stopwatch = new Stopwatch();
        int n = 10;
        Integer[] test = new Integer[]{8, 2, 0, 6, 9, 8, 7, 9, 5, 4};
//        for (int i = 0; i < n; i++) {
//            test[i] = StdRandom.uniform(n);
//        }
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin sort:");
        Selection.sort(test);
        System.out.println("after test:");
        Selection.show(test);
        System.out.println(Selection.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
