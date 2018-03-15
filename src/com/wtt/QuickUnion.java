package com.wtt;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * N^2
 * 最终结果显示的是一片森林，由几颗树组成
 * 整数对为 0 1, 0 2, 0 3, 0 4, 0 5
 * 最终为一颗树[1,2,3,4,5,5]，0的深度最深为5，1的深度为4，索引5是根节点。
 * 整数对为 1 0, 2 0, 3 0, 4 0, 5 0
 * 最终为一颗树[0,0,0,0,0,0], 0是根节点，其他节点深度都是1.
 * 整数对为 0 1, 1 2, 2 3, 3 4, 4 5
 * 结果为 [1,2,3,4,5,5],与01,02,03,04,05相同，
 * 即02和12的连接效果一样，操作的都是根节点，这2棵树是完全一样的
 */
public class QuickUnion {

    private int[] componentArr; //索引是触点，元素值是父连接的索引
    private int count;

    public QuickUnion(int count) {
        this.count = count;
        componentArr = new int[count];
        for (int i = 0; i < count; i++) {
            componentArr[i] = i;
        }
    }
    public int getCount(){return count;}
    public int find(int p){
        while(componentArr[p] != p) p = componentArr[p];
        return p;
    }
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;
        componentArr[rootP] = rootQ;
        count--;
    }
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    public static void main(String[] args) {

        Stopwatch stopwatch = new Stopwatch();
        // program arguments
        // D:\wb-wtt355306\IdeaProjects\algorithm4thEdition\src\com\wtt\chapter1\UnionFindData.txt
        In in = new In(args[0]);
        int count = in.readInt();
        QuickUnion quickUnion = new QuickUnion(count);
        while(!in.isEmpty()) {

            int p = in.readInt();
            int q = in.readInt();
            if (!quickUnion.isConnected(p, q)) {
                quickUnion.union(p, q);
                System.out.println(p + "," + q);
            }
        }
        System.out.println(quickUnion.getCount() + " component");
        System.out.println(Arrays.toString(quickUnion.componentArr));
        System.out.println(stopwatch.elapsedTime());
        // [1, 1, 1, 8, 3, 0, 5, 1, 8, 8]
        // union方法中sideArr[rootP] = rootQ  p的根节点变成q的根节点
        // 以q为根节点, 由union打印出的有效连接可以看出，2个分量的根节点为1和8
        // 这个结果与QuickFind的传递分量得到的1,8结果相同
        // 与之相比，unionFind加上索引这个维度，就可以得到整个连通网络的具体链接情况，即一颗树。
        // 但是，由于树的限制（归并是根节点层面的操作），最终的网络结构与整数对的输入链接情况不是一致的
        // 即最终结果[1,2,3,4,5,5]的输入可能有多个，
        // 可能为01,02,03,04,05
        // 也可能为01,12,23,34,45
        // 一个输出对应多个输入，不同的输入可以形成相同的树
        // 同时可以看出QuickUnion的结果，除了某个触点属于哪个分量的信息外，不能比QuickFind提供更多的信息。它们在最坏的情况都是N^2数量级
    }
}
