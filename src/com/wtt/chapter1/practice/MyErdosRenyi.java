package com.wtt.chapter1.practice;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class MyErdosRenyi {

    public static int count(int N){

        UnionFindCompressPath unionFindCompressPath = new UnionFindCompressPath(N);
        int sideCount = 0;
        while (unionFindCompressPath.getSideCount() > 1) {

            sideCount++;
            int a = StdRandom.uniform(N);
            int b = StdRandom.uniform(N);

            if (!unionFindCompressPath.connected(a, b)) {
                unionFindCompressPath.union(a, b);
            }
        }
        return sideCount;
    }

    public static void main(String[] args) {
        System.out.println(count(10));
    }
}
class UnionFindCompressPath{

    private int[] parents;
    private int[] sideSizes;
    private int sideCount;

    public UnionFindCompressPath(int sideCount) {
        this.sideCount = sideCount;
        parents = new int[sideCount];
        sideSizes = new int[sideCount];
        for (int i = 0; i < sideCount; i++) {
            parents[i] = i;
            sideSizes[i] = 1;
        }
    }
    public int getSideCount(){return sideCount;}
    public boolean connected(int p, int q){return find(p) == find(q);}
    public int find(int p){
        int initialP = p;
        while(parents[p] != p) p = parents[p];
        for (int x = initialP; x != p;) {
            int xParentIndex = parents[x];
            parents[x] = p;
            x = xParentIndex;
        }
        return p;
    }
    public void union(int p, int q){

        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (sideSizes[pRoot] < sideSizes[qRoot]) {
            parents[pRoot] = qRoot;
            sideSizes[qRoot] += sideSizes[pRoot];
        }else{
            parents[qRoot] = pRoot;
            sideSizes[pRoot] += sideSizes[qRoot];
        }
        sideCount--;
    }
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        // program arguments
        // D:\wb-wtt355306\IdeaProjects\algorithm4thEdition\src\com\wtt\chapter1\UnionFindData.txt
        In in = new In(args[0]);
        int count = in.readInt();
        UnionFindCompressPath quickUnion = new UnionFindCompressPath(count);
        while (!in.isEmpty()) {

            int p = in.readInt();
            int q = in.readInt();
            if (!quickUnion.connected(p, q)) {
                quickUnion.union(p, q);
                System.out.println(p + "," + q);
            }
        }
        System.out.println(quickUnion.getSideCount() + " component");
        System.out.println(Arrays.toString(quickUnion.parents));
        System.out.println(Arrays.toString(quickUnion.sideSizes));
        System.out.println(stopwatch.elapsedTime());
    }
}
