package com.wtt.chapter2.practice;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 由于最终需要得到的是一个升序的索引排列index permutation
 * 设其为p,则arr[p[0]], arr[p[1]], arr[p[2]],...
 * 是一个升序序列。
 * <p>
 * 针对该索引序列p进行分治法，
 * 构造辅助数组int[] aux存储归并后的索引，
 * 左右索引子数组所代表的arr中元素是有序的，
 * 即左右子数组中的索引已经是正确(“有序”)的，
 * 此时再调用merge方法进行归并即可得到整个数组正确的索引顺序
 * 2018/3/19 11:12 add by wutaotao
 */
public class MergeIndexSort {

    public static int[] sort(Comparable[] arr) {

        int[] indexPermutation = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            indexPermutation[i] = i;
        }
        int[] aux = new int[arr.length];
        sort(arr, aux, indexPermutation, 0, arr.length - 1);
        return indexPermutation;
    }

    public static void sort(Comparable[] arr, int[] aux, int[] indexPermutation, int lo, int hi) {

        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, indexPermutation, lo, mid);
        sort(arr, aux, indexPermutation, mid + 1, hi);
        merge(arr, aux, indexPermutation, lo, mid, hi);
    }

    public static void merge(Comparable[] arr, int[] aux, int[] indexPermutation, int lo, int mid, int hi) {

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = indexPermutation[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                indexPermutation[k] = aux[j++];   // 左半边耗尽，取右半边数组已经正确的索引顺序
            } else if (j > hi) {
                indexPermutation[k] = aux[i++];
            } else if (less(arr[aux[j]], arr[aux[i]])) { // 由索引代表的元素值进行比较得到正确的索引顺序
                indexPermutation[k] = aux[j++];
            } else {
                indexPermutation[k] = aux[i++];
            }
        }
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        Integer[] test = new Integer[]{8, 2, 0, 6, 9, 10, 7, 1, 5, 4};
        System.out.println("before test:");
        System.out.println(Arrays.toString(test));
        System.out.println("begin test:");
        int[] resultSort = MergeIndexSort.sort(test);
        System.out.println("after test:");
        System.out.println(Arrays.toString(resultSort));
        System.out.println(stopwatch.elapsedTime());
    }
}
