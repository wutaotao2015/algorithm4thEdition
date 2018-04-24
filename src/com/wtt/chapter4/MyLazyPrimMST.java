package com.wtt.chapter4;

import com.wtt.chapter1.practice.Queue;
import edu.princeton.cs.algs4.MinPQ;

/**
 * minimum spanning tree的prim算法延时实现
 * <p>
 * 最小生成树需要包括全部顶点，
 * 从顶点0开始出发，将所有0的相邻边myEdge对象加入最小优先队列，
 * 出队时判断该边的2个顶点是否都在MST中，若是，则删除出队列即可，
 * 若不是，则将该顶点的的另一个顶点的相邻边对象加入队列。
 * <p>
 * 即由点到边，再到该边的另一个顶点，再找其相邻的边，
 * 这个不断扩散的过程，即不断切分的过程，
 * <p>
 * 切分是指将图中的所有顶点分为2个非空且不重复的集合。链接2个集合的边为横切边，
 * 即切分是从顶点出发的，最开始的极端切分情形即为一个集合只有一个顶点0，其他
 * 所有顶点为第2个集合，这时横切边为所有有0的相邻边，找出最小权重边后，该集合
 * 变为2个点，横切边集合进一步扩大，再次进行权重比较，找出第2条边，重复此过程，
 * 直到所有的顶点都被标记，minPQ没有新元素进入，minPQ一直在出队操作，直到循环结束。
 * <p>
 * 2018/4/23 17:59 add by wutaotao
 */
public class MyLazyPrimMST {

    private boolean[] marked; //判断顶点是否已经在MST中
    private Queue<MyEdge> mstQueue; //用来存储MST中的边
    private MinPQ<MyEdge> minPQ; // 横切边集合最小优先队列

    public MyLazyPrimMST(MyEdgeWeightedGraph graph) {

        marked = new boolean[graph.V()];
        mstQueue = new Queue<>();
        minPQ = new MinPQ<>();

        visit(graph, 0);
        while (minPQ.size() > 0) {

            MyEdge minEdge = minPQ.delMin();
            int v = minEdge.either();
            int w = minEdge.other(v);
            if (marked[v] && marked[w]) continue;
            mstQueue.enqueue(minEdge);
            if (!marked[v]) visit(graph, v);
            if (!marked[w]) visit(graph, w);
        }
    }

    private void visit(MyEdgeWeightedGraph graph, int v) {

        marked[v] = true;
        for (MyEdge myEdge : graph.adj(v)) {
            if (!marked[myEdge.other(v)]) minPQ.insert(myEdge);
        }
    }

    public Iterable<MyEdge> edges() {
        return mstQueue;
    }

    public double weight() {
        double weightSum =  0;
        for (MyEdge myEdge : mstQueue) {
            weightSum += myEdge.weight();
        }
        return weightSum;
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

        MyLazyPrimMST myLazyPrimMST = new MyLazyPrimMST(myEdgeWeightedGraph);
        for (MyEdge edge : myLazyPrimMST.edges()) {
            System.out.println(edge);
        }
    }
}
