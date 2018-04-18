package com.wtt.chapter4;

import edu.princeton.cs.algs4.Stack;

/**
 * 寻找有向图中的任意一个有向环
 *
 * 2018/4/18 10:36 add by wutaotao
 */
public class MyDirectedCycle {

    private boolean[] marked;
    private int[] edges;      // 全局的有向边关系
    private boolean[] onStack; // 将深度优先搜索的内部栈显示定义出来，从而可以判断某个顶点是否在栈中
    private Stack<Integer> cycle;

    public MyDirectedCycle(MyDigraph digraph) {

        marked = new boolean[digraph.V()];
        edges = new int[digraph.V()];
        onStack = new boolean[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {

            if (!marked[i] && !hasCycle()) dfs(digraph, i);
        }
    }
    private void dfs(MyDigraph digraph, int v) {
        // 入栈，递归方法开始入栈
        onStack[v] = true;
        marked[v] = true;
        for (Integer w : digraph.adj(v)) {
            if (hasCycle()) return;
            if (!marked[w]) {
                edges[w] = v;
                dfs(digraph, w);
            }else if (onStack[w]) {
                // 有向环存在
                cycle = new Stack<>();
                // 找到w已经在栈中，说明栈中存在一条w指向v的路径，现在v->w补全了路径成为有向环
                // edges[i]保存的是指向它的顶点值，所以需要逆推，从v到w
                for (int x = v; x != w; x = edges[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        // 出栈,递归方法执行完毕后出栈
        onStack[v] = false;
    }
    public boolean hasCycle() {
        return cycle != null;
    }
    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        MyDigraph myDigraph = new MyDigraph(4);
        myDigraph.addEdge(0, 1);
        myDigraph.addEdge(1, 2);
//        myDigraph.addEdge(2, 1);
        myDigraph.addEdge(2, 3);
        myDigraph.addEdge(3, 1);
        MyDirectedCycle myDirectedCycle = new MyDirectedCycle(myDigraph);
        if (myDirectedCycle.hasCycle()) {
            for (Integer i : myDirectedCycle.cycle()) {
                System.out.print(i + " ");
            }
        }else {
            System.out.println("no cycle");
        }
    }
}
