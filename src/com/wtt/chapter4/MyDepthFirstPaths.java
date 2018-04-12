package com.wtt.chapter4;

import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

/**
 * 深度优先的路径查找
 * 该算法关键在于将路径中的边转化为与quickUnion中相同的父链接数组
 * 通过顶点为索引，第一次经过它时的边（对应顶点）的值为元素值的数组可以得到以起点s为根，且包括了所有与s连通的顶点的树，不连通的顶点值为也为0。
 * 所以可以遍历所有顶点，通过hasPathTo进行判断是否连通，将所有连通的路径用pathTo打印出来
 *
 *
 * 2018/4/12 9:06 add by wutaotao
 */
public class MyDepthFirstPaths {

    private boolean[] marked;
    private int[] edges;
    private final int s;

    public MyDepthFirstPaths(MyGraph graph, int s) {
        marked = new boolean[graph.V()];
        edges = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }
    private void dfs(MyGraph graph, int v) {

        marked[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                edges[w] = v;
                dfs(graph, w);
            }
        }
    }
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edges[x]) {
           stack.push(x);
        }
        stack.push(s);
        return stack;
    }
    public String getEdges() {
        return Arrays.toString(edges);
    }

    public static void main(String[] args) {

        MyGraph myGraph = new MyGraph(6);
        myGraph.addEdge(0, 2);
        myGraph.addEdge(1, 2);
//        myGraph.addEdge(3, 2);
        myGraph.addEdge(5, 4);
        myGraph.addEdge(5, 3);
        MyDepthFirstPaths myDepthFirstPaths = new MyDepthFirstPaths(myGraph, 0);
        System.out.println(myDepthFirstPaths.getEdges());
        for (int i = 0; i < myGraph.V(); i++) {
            System.out.print("0 - " + i + ": ");
            if (myDepthFirstPaths.hasPathTo(i)) {
                for (Integer x : myDepthFirstPaths.pathTo(i)) {
                    if (x == 0) System.out.print(x);
                    else System.out.print("-" + x);
                }
            }
            System.out.println();
        }
    }
}
