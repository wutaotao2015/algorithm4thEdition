package com.wtt.chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 堆排序是唯一能同时最优的利用空间和时间的排序算法
 * 只需少于2NlgN+2N次比较，以及一半次数的交换
 * <p>
 * 一般情况比快速排序慢，但最坏情况比快排好
 * 对于堆有序以后的去除最大值，将其与最后一个元素交换再下沉恢复堆有序的过程中，
 * 很明显可以想到最后一个元素下沉到的最终位置很有可能是最后一层，所以可以在下沉
 * 过程中将其直接与子节点中较大者进行交换到达底层后再上浮的操作，这样可以减少一半的比较次数，
 * 接近归并排序的比较次数。
 * Created by wutaotao
 * 2018/3/23 22:43
 */
public class MyHeap {

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(arr, k, n);
        }
        while (n > 1) {
            exch(arr, 1, n--);
            sink(arr, 1, n);
        }
    }

    public static void sink(Comparable[] arr, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            while (j < n && less(arr, j, j + 1)) j++;
            if (!less(arr, k, j)) break;
            exch(arr, k, j);
            k = j;
        }
    }

    public static boolean less(Comparable[] arr, int i, int j) {
        return arr[i - 1].compareTo(arr[j - 1]) < 0;
    }

    public static void exch(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i - 1];
        arr[i - 1] = arr[j - 1];
        arr[j - 1] = tmp;
    }

    public static boolean isSorted(Comparable[] test) {
        return true;
    }

    public static void main(String[] args) {

        Stopwatch stopwatch = new Stopwatch();
        Integer[] test = new Integer[]{8, 2, 0, 6, 9, 1, 7, 3, 5, 4};
//        int n = 10000;
//        Integer[] test = new Integer[n];
//        for (int i = 0; i < n; i++) {
//            test[i] = StdRandom.uniform(n);
//        }
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin test:");
        MyHeap.sort(test);
        System.out.println("after test:");
        System.out.println(Arrays.toString(test));
        System.out.println(MyHeap.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
