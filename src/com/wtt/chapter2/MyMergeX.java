package com.wtt.chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 针对归并排序进行优化
 * 1. 小数组不用归并，用插入排序更快
 * 2. 左右两边都排好序后，若arr[mid] < arr[mid + 1],说明整个数组已经有序，可以跳过merge方法
 * 3. 不用复制元素
 * <p>
 * 利用对称性
 * 原流程是左右子数组各自变成有序后，再到merge方法中从arr复制数据到aux数组，在aux数组中进行比较后赋值到arr原数组中，从而得到有序的arr数组
 * 新流程利用对称性，递归调用sort,在递归时左右子数组调用sort方法得到有序的src,
 * 在merge中从有序的src得到有序的dst（若跳过merge，直接复制src数组即可）,这样一正一反，
 * 即在左右子数组一层排好序后，通过归并或直接复制到另一个数组中得到有序的数组，比原来先从arr复制到aux,在aux中比较完后再归并到arr的方式
 * 减少了第一步复制的操作。
 * 这样就实现了不用复制元素到辅助数组，直接将元素排序到另一个数组的目的，从而提高了效率
 * <p>
 * Created by wutaotao
 * 2018/3/18 11:03
 */
public class MyMergeX {

    public static void sort(Comparable[] arr) {
        //auxiliary array
        // clone方法保证arr,aux的初始元素是一样的，从而为对称性提供了保障。
        Comparable[] aux = arr.clone();
        sort(aux, arr, 0, arr.length - 1);
    }

    /**
     * 从递归调用的格式sort(aux, arr, 0, arr.length - 1)
     * 可以得出最终需要有序的是第二个参数dst
     *
     * @param src
     * @param dst
     * @param lo
     * @param hi
     */
    public static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {

        if (lo + 7 >= hi) {
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        // 将元素排序到src数组中
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);
        if (!less(src[mid], src[mid + 1])) {
            merge(src, dst, lo, mid, hi);
        } else {
            // 经过上面的左右子数组递归调用sort排序后，可以得到有序的src
            // 所以需要将src拷贝到dst以得到需要的排序数组dst
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
        }
    }

    public static void insertionSort(Comparable[] arr, int lo, int hi) {

        for (int i = lo + 1; i <= hi; i++) {
            Comparable ins = arr[i];
            int j;
            for (j = i; j > lo && less(ins, arr[j - 1]); j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = ins;
        }
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (less(src[j], src[i])) {
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
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
        MyMergeX.sort(test);
        System.out.println("after test:");
        System.out.println(MyMergeX.isSorted(test));
        System.out.println(stopwatch.elapsedTime());
    }
}
