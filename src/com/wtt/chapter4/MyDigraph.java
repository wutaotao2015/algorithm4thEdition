package com.wtt.chapter4;

import com.wtt.chapter1.practice.Bag;
import edu.princeton.cs.algs4.Stack;

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

    public MyDigraph(MyDigraph digraph) {

        this(digraph.V());
        this.E = digraph.E();
        for (int i = 0; i < digraph.V(); i++) {
            // 使用栈保证调用adj时顶点的遍历顺序与原对象相同
            Stack<Integer> stack = new Stack<>();
            for (Integer v : digraph.adj(i)) {
                stack.push(v);
            }
            for (Integer w : stack) {
                adj[i].add(w);
            }
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
