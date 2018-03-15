package com.wtt.chapter1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * N^2
 * 连通的最终结果只能显示最后剩下几个分量，
 * 分量值显示的是该分量中最后添加的触点值
 * 整数对为 0 1, 0 2, 0 3, 0 4, 0 5
 * 结果为 [5,5,5,5,5,5]
 * 整数对为 1 0, 2 0, 3 0, 4 0, 5 0
 * 结果为 [0,0,0,0,0,0]
 * 整数对为 0 1, 1 2, 2 3, 3 4, 4 5
 * 结果为 [5,5,5,5,5,5]
 */
public class QuickFind {

    private int[] componentArr;
    private int count;

    public QuickFind(int n) {
        componentArr = new int[n];
        for (int i = 0; i < n; i++) {
            componentArr[i] = i;
        }
        count = n;
    }

    public int find(int p) {
        return componentArr[p];
    }

    public void union(int p, int q) {
        int pSide = find(p);
        int qSide = find(q);
        if (pSide == qSide) return;

        for (int i = 0; i < componentArr.length; i++) {
            if (componentArr[i] == pSide) componentArr[i] = qSide;
        }
        count--;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {

        Stopwatch stopwatch = new Stopwatch();
        // program arguments
        // D:\wb-wtt355306\IdeaProjects\algorithm4thEdition\src\com\wtt\chapter1\UnionFindData.txt
        In in = new In(args[0]);
        int n = in.readInt();
        QuickFind quickFind = new QuickFind(n);
        while (!in.isEmpty()) {

            int p = in.readInt();
            int q = in.readInt();
            if (!quickFind.isConnected(p, q)) {
                System.out.println(p + "," + q);
//                System.out.println(Arrays.toString(quickFind.arr));
                quickFind.union(p, q);
//                System.out.println(Arrays.toString(quickFind.arr));
            }
        }
        System.out.println(quickFind.count() + " component");
        System.out.println(Arrays.toString(quickFind.componentArr));
        System.out.println(stopwatch.elapsedTime());
        // [1, 1, 1, 8, 8, 1, 1, 1, 8, 8]
        // if(sideArr[p] == sideP) sideArr[p] = sideQ, p的分量变成q
        // 分量的赋值得到1和8是传递性的结果
    }
}
