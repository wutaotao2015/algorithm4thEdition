package com.wtt.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 对于普通的插入排序，当一个较小的数在数组后面时，经过两两交换到前方十分耗费性能
 * 利用递增序列1，4，13，40， 121， 364，。。。将原始数组划分成嵌套的子数组（部分有序）
 * 当h==1时即执行普通的插入排序，但此时数组已经基本有序，时间大大减少。
 * 此递增序列可以将运行时间减少为N^1.5数量级
 *
 * Created by wutaotao
 * 2018/3/17 15:28
 */
public class MyShell {
    public static void sort(Comparable[] arr){

        int n = arr.length;
        int h = 1;

        while(h < n/3) h = h * 3 + 1;
        while(h >= 1) {

            for (int i = h; i < n; i++) {

                for (int j = i; j >= h && less(arr[j], arr[j - h]) ; j -= h) {
                    exch(arr, j, j - h);
                }
            }
            h = h / 3;
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
    public static String show(Comparable[] arr) {
        return Arrays.toString(arr);
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
        MyShell.sort(test);
        System.out.println("after test:");
        MySelection.show(test);
        System.out.println(MySelection.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
