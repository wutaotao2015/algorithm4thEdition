package com.wtt.chapter4;

import com.wtt.chapter2.practice.IndexMinPQ;

import java.util.Arrays;

/**
 * 即时版本的prim算法
 *
 *  延时版本是先调用visit(G,0),再进行优先级队列出队操作，这样在循环过程中，通过visit中不断mark将顶点加入到MST中，
 *  队列中会存在无效的边
 *  而即时版本中是先进行出队操作，再去进行mark标记操作，从而避免了无效边加入到队列中，同时加入时通过比较权重
 *  （通过顶点作为索引来定位该顶点到树的最小权重，因为随着树的增大，该顶点到树的路径选择变多，从而权重变小的可能增加）
 *  ，只加入最小权重的一条边，减少了队列中的边数，从而提高了队列的操作速度。
 *
 *  edgeTo和disTo的第一个元素为null和0.0,代表着顶点0作为树的起始点，不计入具体的对应关系，即这样剩下的V-1个顶点才可以对应MST的V-1条边
 *
 * 2018/4/24 17:04 add by wutaotao
 */
public class MyEagerPrimMST {

    private MyEdge[] edgeTo;  // 索引对应顶点的最小权重边，也是MST的边，其值随树的增长而变化
    private double[] distTo;     // 索引对应顶点到MST的最小权重边的权重，其值随树的增长而变化，最后结果与mstEdgeTo一致
    private boolean[] marked;   // 顶点是否在树中
    private IndexMinPQ<Double> indexMinPQ;  // 索引对应顶点到树的最小权重的索引优先队列，里面的边没有无效边，当停止入队时，mst中所有边就确定了

    public MyEagerPrimMST(MyEdgeWeightedGraph graph) {

        edgeTo = new MyEdge[graph.V()];
        distTo = new double[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        marked = new boolean[graph.V()];
        indexMinPQ = new IndexMinPQ<>(graph.V());

        // edgeTo[0] = null;
        distTo[0] = 0.0; // 权重有可能是负值
        indexMinPQ.insert(0, 0.0); //顶点0的key为0.0，顶点x的key为x到树的最小权重
        while (!indexMinPQ.isEmpty()) {
            visit(graph, indexMinPQ.delMin());
        }
    }
    private void visit(MyEdgeWeightedGraph graph, int v) {

        marked[v] = true;
        for (MyEdge myEdge : graph.adj(v)) {

            int w = myEdge.other(v);
            if (marked[w]) continue;
            double myEdgeWeight = myEdge.weight();
            if (myEdgeWeight < distTo[w]) {
                edgeTo[w] = myEdge;
                distTo[w] = myEdgeWeight;
                // 判断优先队列中是否已经包含w的索引
                if (indexMinPQ.contains(w)) indexMinPQ.changeKey(w, myEdgeWeight);
                else indexMinPQ.insert(w, myEdgeWeight);
            }
        }
    }
    public Iterable<MyEdge> edges() {
        return Arrays.asList(edgeTo);
    }
    public static void main(String[] args) {

        MyEdgeWeightedGraph myEdgeWeightedGraph = new MyEdgeWeightedGraph(8);
        MyEdge myEdge = new MyEdge(0, 7, 0.16);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(0, 2, 0.26);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(0, 4, 0.38);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(0, 6, 0.58);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(7, 1, 0.19);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(7, 5, 0.28);
        myEdgeWeightedGraph.addEdge(myEdge);
//        myEdge = new MyEdge(7, 2, 0.34);
//        myEdgeWeightedGraph.addEdge(myEdge);
//        myEdge = new MyEdge(7, 4, 0.37);
//        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(1, 3, 0.29);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(1, 5, 0.32);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(1, 2, 0.36);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(2, 3, 0.17);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(2, 6, 0.40);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(3, 6, 0.52);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(4, 5, 0.35);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(4, 6, 0.93);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(7, 2, 0.34);
        myEdgeWeightedGraph.addEdge(myEdge);
        myEdge = new MyEdge(7, 4, 0.37);
        myEdgeWeightedGraph.addEdge(myEdge);

        MyEagerPrimMST mst = new MyEagerPrimMST(myEdgeWeightedGraph);
        for (MyEdge edge : mst.edges()) {
            System.out.println(edge);
        }
    }
}
