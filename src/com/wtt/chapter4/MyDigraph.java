package com.wtt.chapter4;

import com.wtt.chapter1.practice.Bag;

/**
 * 2018/4/17 16:45 add by wutaotao
 */
public class MyDigraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public MyDigraph(int V) {

        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }
    public int V() {return V;}
    public int E() {return E;}
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    public MyDigraph reverse() {

        MyDigraph reverseDGP = new MyDigraph(V);
        for (int v = 0; v < V; v++) {
            for (Integer w : adj[v]) {
                reverseDGP.addEdge(w, v);
            }
        }
        return reverseDGP;
    }
}
