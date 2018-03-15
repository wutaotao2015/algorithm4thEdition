package com.wtt.chapter1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 由于QuickUnion的建树过程中，可能将大树归并到小树上，
 * 这个过程如果不断累加进行，会导致以前的大树节点的深度
 * 非常深，从而导致find该节点的时间消耗增多，从而影响性能。
 * 所以采取每次归并树都是小树归并到大树上的策略，
 * 从而将任意节点的深度控制在lgN上。
 * <p>
 * 大树归并到小树上，该树纵向扩展，增加树的高度。
 * 小树归并到大树上，该树横向扩展，树的高度不变。
 */
public class WeightingQuickUnion {

    private int[] componentArr; //索引是触点，元素值是父连接索引
    private int[] componentSize; //对应根节点的分量大小，基础是1，作为根节点一次加1
    private int count;

    public WeightingQuickUnion(int count) {
        this.count = count;
        componentArr = new int[count];
        componentSize = new int[count];
        for (int i = 0; i < count; i++) {
            componentSize[i] = 1;
            componentArr[i] = i;
        }
    }

    public int getCount() {
        return count;
    }

    public int find(int p) {
        while (componentArr[p] != p) p = componentArr[p];
        return p;
    }

    public void union(int p, int q) {

        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;
        if (componentSize[rootP] < componentSize[rootQ]) {
            componentArr[rootP] = rootQ;
            componentSize[rootQ] += componentSize[rootP];
        } else {
            componentArr[rootQ] = rootP;
            componentSize[rootP] += componentSize[rootQ];
        }
        count--;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        // program arguments
        // D:\wb-wtt355306\IdeaProjects\algorithm4thEdition\src\com\wtt\chapter1\UnionFindData.txt
        In in = new In(args[0]);
        int count = in.readInt();
        WeightingQuickUnion quickUnion = new WeightingQuickUnion(count);
        while (!in.isEmpty()) {

            int p = in.readInt();
            int q = in.readInt();
            if (!quickUnion.isConnected(p, q)) {
                quickUnion.union(p, q);
                System.out.println(p + "," + q);
            }
        }
        System.out.println(quickUnion.getCount() + " component");
        System.out.println(Arrays.toString(quickUnion.componentArr));
        System.out.println(Arrays.toString(quickUnion.componentSize));
        System.out.println(stopwatch.elapsedTime());
        // [6, 2, 6, 4, 4, 6, 6, 2, 4, 4] 比[1, 1, 1, 8, 3, 0, 5, 1, 8, 8]少一层，只有2层
        // componentSizeArr [1, 1, 3, 1, 4, 1, 6, 1, 1, 1]
        // 该数组展示了每个索引作为根节点时所在树的总节点数
    }
}
