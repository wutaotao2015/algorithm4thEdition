package com.wtt.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 插入排序时间受输入数据影响很大，
 * 最好的情况是数据已经全部有序，需要N-1次比较，0次交换
 * 最坏的情况是数据是倒序排列，需要N^2/2次比较，N^2/2次交换
 * 即平均N^2/4次比较和N^2/4次交换
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
        for (int i = 1; i < arr.length; i++) {
            Comparable ins = arr[i];
            int j;
            for (j = i - 1; j >= 0 && less(ins, arr[j]); j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = ins;
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
