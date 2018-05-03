package com.wtt.chapter4;

import com.wtt.chapter1.practice.Bag;

/**
 * 2018/4/27 16:18 add by wutaotao
 */
public class MyEdgeWeightedDigraph {

    private int v;
    private int e;
    private Bag<MyDirectedEdge>[] adj;

    public MyEdgeWeightedDigraph(int v) {

        v = this.v;
        e = 0;
        adj = (Bag<MyDirectedEdge>[])new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }
    public void addEdge(MyDirectedEdge edge) {
        int v = edge.from();
        adj[v].add(edge);
        e++;
    }
    public int V(){
        return v;
    }
    public int E(){
        return e;
    }
    public Iterable<MyDirectedEdge> adj(int v) {
        return adj[v];
    }
    public Iterable<MyDirectedEdge> edges(){
        Bag<MyDirectedEdge> bag = new Bag<>();
        for (int i = 0; i < v; i++) {
            for (MyDirectedEdge myDirectedEdge : adj[i]) {
                bag.add(myDirectedEdge);
            }
        }
        return bag;
    }
}
