package com.wtt.chapter4;

import com.wtt.chapter1.practice.Queue;
import edu.princeton.cs.algs4.MinPQ;

/**
 *  minimum spanning tree的prim算法延时实现
 *
 * 2018/4/23 17:59 add by wutaotao
 */
public class MyLazyPrimMST {

    private boolean[] marked; //判断顶点是否已经在MST中
    private Queue<MyEdge> mstQueue; //用来存储MST中的边
    private MinPQ<MyEdge> minPQ; // 横切边集合

    public MyLazyPrimMST(MyEdgeWeightedGraph graph) {

        marked = new boolean[graph.V()];
        mstQueue = new Queue<>();
        minPQ = new MinPQ<>();

    }
}
