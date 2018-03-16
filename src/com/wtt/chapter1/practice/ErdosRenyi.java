package com.wtt.chapter1.practice;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * *
 *  When n is large, Erdos and Renyi proved that after about 1/2 n ln n
 *  additions, the graph will have a 50/50 chance of being connected.
 *
 */
public class ErdosRenyi {

    public static int count(int n) {
        int edges = 0;
        UF uf = new UF(n);
        while (uf.getSideCount() > 1) {
            int i = StdRandom.uniform(n);
            int j = StdRandom.uniform(n);
            uf.union(i, j);
            edges++;
        }
        return edges;
    }

    public static void main(String[] args) {
        int n = 6400;          // number of vertices
        int trials = 1000;     // number of trials
        int[] edges = new int[trials];

        // repeat the experiment trials times
        for (int t = 0; t < trials; t++) {
            edges[t] = count(n);
        }

        // report statistics
        StdOut.println("1/2 n ln n = " + 0.5 * n * Math.log(n));
        StdOut.println("mean       = " + StdStats.mean(edges));
        StdOut.println("stddev     = " + StdStats.stddev(edges));
    }
}
class UF{

    private int[] parents;
    private int[] sideSizes;
    private int sideCount;

    public UF(int sideCount) {
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
}