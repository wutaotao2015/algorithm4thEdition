
package com.wtt.chapter2.practice;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 首先，频率在变化相当于主键的变化，无法直接排序和使用优先队列
 * 其次，针对主键为频率的字符串，应建立一个以频率为比较值的对象，
 * 根据输入字符串完成对象数组的录入后直接排序即可。
 * 2018/3/26 9:25 add by wutaotao
 */
public class Frequence {

    public static void main(String[] args) {
        // read in and sort the input strings
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        int n = a.length;
        Arrays.sort(a);

        // create an array of distinct strings and their frequencies
        Record[] records = new Record[n];
        String word = a[0];
        int freq = 1;
        int m = 0;
        for (int i = 1; i < n; i++) {
            if (!a[i].equals(word)) {
                records[m++] = new Record(word, freq);
                word = a[i];
                freq = 0;
            }
            freq++;
        }
        records[m++] = new Record(word, freq);

        // sort by frequency and print results
        Arrays.sort(records, 0, m);
        for (int i = m - 1; i >= 0; i--)
            StdOut.println(records[i]);
    }
}

class Record implements Comparable<Record> {
    private String word;
    private int freq;

    public Record(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }

    @Override
    public int compareTo(Record o) {
        if (freq < o.freq) return -1;
        else if (freq > o.freq) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return word + " " + freq;
    }
}
