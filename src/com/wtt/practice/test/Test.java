package com.wtt.practice.test;

import edu.princeton.cs.algs4.Accumulator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by wutaotao
 * 2018/3/6 21:21
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("hello world!");
        int N = 50;
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.random();
        for (int i = 0; i < N; i++)
        {
            double x = 1.0*i/N;
            double y = a[i]/2.0;
            double rw = 0.5/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
