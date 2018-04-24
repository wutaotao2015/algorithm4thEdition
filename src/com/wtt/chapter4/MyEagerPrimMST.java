package com.wtt.chapter4;

import com.wtt.chapter2.practice.IndexMinPQ;

/**
 * 即时版本的prim算法
 *
 *  延时版本是先调用visit(G,0),再进行优先级队列出队操作，这样在循环过程中，通过visit中不断mark将顶点加入到MST中，
 *  队列中会存在无效的边
 *  而即时版本中是先进行出队操作，再去进行mark标记操作，从而避免了无效边加入到队列中，同时加入时通过比较权重
 *  （通过顶点作为索引来定位该顶点到树的最小权重，因为随着树的增大，该顶点到树的路径选择变多，从而权重变小的可能增加）
 *  ，只加入最小权重的一条边，减少了队列中的边数，从而提高了队列的操作速度。
 *
 * 2018/4/24 17:04 add by wutaotao
 */
public class MyEagerPrimMST {

    private MyEdge[] edgeTo;  // 索引对应顶点的最小权重边，也是MST的边，其值随树的增长而变化
    private double[] distTo;     // 索引对应顶点到MST的最小权重边的权重，其值随树的增长而变化，最后结果与mstEdgeTo一致
    private boolean[] marked;   // 顶点是否在树中
    private IndexMinPQ<Double> minPQ;  // 索引对应顶点到树的最小权重的索引优先队列，里面的边没有无效边，当停止入队时，mst中所有边就确定了

    public MyEagerPrimMST(MyEdgeWeightedGraph graph) {

        edgeTo = new MyEdge[graph.V()];
        distTo = new double[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        marked = new boolean[graph.V()];
        minPQ = new IndexMinPQ<>(graph.V());

        minPQ.insert(0, 0.0); //顶点0的key为0.0，顶点x的key为x到树的最小权重

    }
}
