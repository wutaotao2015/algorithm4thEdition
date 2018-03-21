package com.wtt.chapter2.practice;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 因为归并排序算法会导致数组按升序排列，
 * 为防止改变原数组，需要克隆出一个新数组来进行操作。
 * 2018/3/19 9:38 add by wutaotao
 */
public class MyInversion {

    public static int bruteFindInversion(Comparable[] arr) {

        int count = 0;
        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {

                if (less(arr[j], arr[i])) count++;
            }
        }
        return count;
    }

    public static int count(Comparable[] arr) {
        Comparable[] brr = arr.clone();
        Comparable[] aux = arr.clone();
        return sort(brr, aux, 0, arr.length - 1);
    }

    public static int sort(Comparable[] brr, Comparable[] aux, int lo, int hi) {
        int count = 0;
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        count += sort(brr, aux, lo, mid);
        count += sort(brr, aux, mid + 1, hi);
        count += merge(brr, aux, lo, mid, hi);
        return count;
    }

    public static int merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {

        int count = 0;
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
                // 左右子数组都是有序的，
                // 所以当a[j]<a[i]时，a[j]小于i--mid中的所有元素
                count += (mid - i + 1);
            } else {
                arr[k] = aux[i++];
            }
        }
        return count;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {

        Integer[] test = new Integer[100];
        for (int i = 0; i < 100; i++) {
            test[i] = StdRandom.uniform(100);
        }
        System.out.println(bruteFindInversion(test));
        System.out.println(count(test));
    }
}
