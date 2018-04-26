package com.wtt.chapter4;

import com.wtt.chapter1.practice.Bag;

/**
 * 2018/4/23 13:50 add by wutaotao
 */
public class MyEdgeWeightedGraph {

    private int v;
    private int e;
    private Bag<MyEdge>[] adj;

    public MyEdgeWeightedGraph(int v) {
        this.v = v;
        adj = (Bag<MyEdge>[])new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }
    public int V(){
        return v;
    }
    public int E(){
        return e;
    }
    // 顶点值作为索引保存2个edge对象的引用
    public void addEdge(MyEdge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        e++;
    }
    public Iterable<MyEdge> adj(int v) {
        return adj[v];
    }
    // 2个引用手动筛选，通过比较顶点索引大小去掉一个MyEdge引用
    public Iterable<MyEdge> adj() {

        Bag<MyEdge> b = new Bag<>();
        for (int i = 0; i < v; i++) {
            for (MyEdge myEdge : adj[i]) {
                if (myEdge.other(i) > i) b.add(myEdge);
            }
        }
        return b;
    }
    public Iterable<MyEdge> edges() {

        Bag<MyEdge> resBag = new Bag<>();
        for (int i = 0; i < v; i++) {
            for (MyEdge myEdge : adj(i)) {
                resBag.add(myEdge);
            }
        }
        return resBag;
    }
}
