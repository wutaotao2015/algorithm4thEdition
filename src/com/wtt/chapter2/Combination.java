package com.wtt.chapter2;

/**
 * Created by wutaotao
 * 2018/3/17 16:17
 */
public class Combination {

    public static void sort(Comparable[] arr){

        //Selection
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = i + 1; j < n; j++) {
                if (less(arr[j], arr[min])) min = j;
            }
            exch(arr, i, min);
        }
        // 选择排序若写为for(int j = i + 1; j<n && less(arr[j], arr[min]); j++)
        // 这样写无法遍历完后面的（n - i）个元素从而找出最小值，只要有一个j不满足less函数，即会跳出该循环

        //与上面的遍历所有后面的元素找出最小值，插入排序只要找到一个元素比待插入值小就可以跳出循环，
        // 不再进行交换操作
        //Insertion (adjacent swap)
        for(int i = 1; i < n; i++) {
            for(int j = i; j > 0 && less(arr[j], arr[j - 1]); j--){
                exch(arr, j, j - 1);
            }
        }
        //Insertion (right move,no swap)
        for(int i = 1; i < n; i++) {
            Comparable ins = arr[i];
            int j;
            for(j = i; j > 0 && less(ins, arr[j - 1]); j--){
                arr[j] = arr[j - 1];
            }
            arr[j] = ins;
        }
        //Insertion (right move, no swap, one sentinel without j > 0)
        int exchangeCount = 0;
        for (int i = n - 1; i > 0; i--) {
            if (less(arr[i], arr[i - 1])){
                exch(arr, i, i - 1);
                exchangeCount++;
            }
        }
        if (exchangeCount == 0) return;
        for (int i = 2; i < n; i++) {
            Comparable ins = arr[i];
            int j;
            for(j = i; less(ins, arr[j - 1]); j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = ins;
        }

        //Shell
        int h = 1;
        while(h < n/3) h = 3*h + 1;
        while(h >= 1) {
            for(int i = h; i < n; i++) {
                for(int j = i; j >= h && less(arr[j], arr[j - h]); j -= h){
                    exch(arr, j, j-h);
                }
            }
            h = h/3;
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
}
