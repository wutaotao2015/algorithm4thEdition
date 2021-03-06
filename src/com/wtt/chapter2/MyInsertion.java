package com.wtt.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 插入排序时间受输入数据影响很大，
 * 最好的情况是数据已经全部有序，需要N-1次比较，0次交换
 * 最坏的情况是数据是倒序排列，需要N^2/2次比较，N^2/2次交换
 * 即平均N^2/4次比较和N^2/4次交换
 * <p>
 * 将最小的元素放到最左边（作为哨兵）的好处是去掉了边界条件j > 0
 * 因为之前当j = 1时，若仍然满足less条件，右移后j--进入下个循环，从而导致j-1 == -1数组越界，
 * 当有哨兵后，可以确定less条件在j - 1 == 0时一定会不满足条件，从而跳出循环，不会出现j == 0的情况。
 */
public class MyInsertion {

    public static void sort(Comparable[] arr) {

        // 不停交换相邻项元素的插入排序
//        for (int i = 1; i < arr.length; i++) {
//            for (int j = i; j > 0 && less(arr[j], arr[j - 1]) ; j--) {
//                exch(arr, j, j - 1);
//            }
//            System.out.println(Arrays.toString(arr));
//        }
        // 主键大的元素右移的插入排序
        sort(arr, 0, arr.length - 1);
//        for (int i = 1; i < arr.length; i++) {
//            Comparable ins = arr[i];
//            int j;
//            for (j = i; j > 0 && less(ins, arr[j - 1]); j--) {
//                arr[j] = arr[j - 1];
//            }
//            arr[j] = ins;
////            System.out.println(Arrays.toString(arr));
//        }
        // 右移加哨兵
//        int exchCount = 0;
//        for (int i = arr.length - 1; i > 0; i--) {
//            if (less(arr[i], arr[i - 1])) {
//                exch(arr, i, i - 1);
//                exchCount++;
//            }
//        }
//        if (exchCount == 0) return;
//        for (int i = 2; i < arr.length; i++) {
//            Comparable ins = arr[i];
//            int j;
//            for (j = i; less(ins, arr[j - 1]); j--) {
//                arr[j] = arr[j - 1];
//            }
//            arr[j] = ins;
//        }
    }

    public static void sort(Comparable[] arr, int lo, int hi) {

        for (int i = lo + 1; i <= hi; i++) {
            Comparable ins = arr[i];
            int j;
            for(j = i; j > lo && less(ins, arr[j - 1]); j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = ins;
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

    public static void show(Comparable[] arr) {
        for (Comparable a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
//        int n = 10000;
//        Integer[] test = new Integer[n];
//        for (int i = 0; i < n; i++) {
//            test[i] = StdRandom.uniform(n);
//        }
        Integer[] test = new Integer[]{8, 2, 0, 6, 9, 8, 7, 9, 5, 4};
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin test:");
        MyInsertion.sort(test);
        System.out.println("after test:");
        MySelection.show(test);
        System.out.println(MySelection.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
