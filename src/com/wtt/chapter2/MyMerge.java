package com.wtt.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 归并排序
 * 分治法思想
 * 通过递归将左右2个已经有序的子数组逐个比较再归并回原数组得到正确排序的思想
 * 它的比较次数为N/2logN 到 NlogN之间。
 * 访问数组次数最多为6NlogN次（2N复制数组，2N归并回原数组，2N次比较（左右子数组归并n次，整个数组n次））。
 * Created by wutaotao
 * 2018/3/18 10:20
 */
public class MyMerge {

    public static void sort(Comparable[] arr) {

        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    public static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {

        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 原地归并
     * 将2个已经有序的子数组归并成一个大数组
     *
     * @param arr
     * @param lo
     * @param hi
     */
    public static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        for (int k = lo; k <= hi; k++) {
            //左边耗净
            if (i > mid) {
                arr[k] = aux[j++];
            }
            //右边耗净
            else if (j > hi) {
                arr[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])) {
                arr[k] = aux[j++];
            }
            //左右两边的元素相等时取左边的元素
            else {
                arr[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        Integer[] test = new Integer[]{8, 2, 0, 6, 9, 8, 7, 9, 5, 4};
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin test:");
        MyMerge.sort(test);
        System.out.println("after test:");
        MySelection.show(test);
        System.out.println(MySelection.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
