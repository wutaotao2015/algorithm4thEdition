package com.wtt.chapter4;

import com.wtt.chapter1.practice.Queue;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.UF;

/**
 * 2018/4/26 14:14 add by wutaotao
 */
public class MyKruskalMST {

    private Queue<MyEdge> mstQueue;
    private MinPQ<MyEdge> minPQ;
    private UF uf;
    private double weight;

    public MyKruskalMST(MyEdgeWeightedGraph graph) {

        mstQueue = new Queue<>();
        minPQ = new MinPQ<>();
        for (MyEdge myEdge : graph.edges()) {
            minPQ.insert(myEdge);
        }
        uf = new UF(graph.V());

        while(minPQ.size() > 0 && mstQueue.size() < graph.V() - 1) {

            MyEdge myEdge = minPQ.delMin();
            int v = myEdge.either();
            int w = myEdge.other(v);
            if (!uf.connected(v, w)){
                mstQueue.enqueue(myEdge);
                uf.union(v, w);
                weight += myEdge.weight();
            }
        }
    }
    public double weight() {
        return weight;
    }
    public Iterable<MyEdge> edges() {
        return mstQueue;
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

        MyKruskalMST kruskalMST = new MyKruskalMST(myEdgeWeightedGraph);
        for (MyEdge edge : kruskalMST.edges()) {
            System.out.println(edge);
        }
    }
}
