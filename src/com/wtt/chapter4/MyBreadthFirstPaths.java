package com.wtt.chapter4;

import com.wtt.chapter1.practice.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

/**
 * 广度优先搜索
 *
 * 与深度优先递归队列搜素递归方法隐藏的栈（先入后出)不同，广度优先查找方法是先进先出，显式的利用队列来完成联通的顶点遍历
 * 即深度优先是先取最后一个出现的相邻节点（由遍历相邻节点时其是bag实现，联通头部的操作），
 * 广度优先是先取第一个出现的相邻节点（遍历
 *
 * Created by wutaotao
 * 2018/4/12 21:08
 */
public class MyBreadthFirstPaths {

    private boolean[] marked;
    private int[] edges;
    private int s;

    public MyBreadthFirstPaths(MyGraph graph, int s) {

        marked = new boolean[graph.V()];
        edges = new int[graph.V()];
        this.s = s;
        bfs(graph, s);
    }

    private void bfs(MyGraph graph, int s) {

        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);

        while(queue.size() > 0) {
            // 从起点开始逐一推出队列，标记后将其相邻节点放入队列中
            Integer v = queue.dequeue();
            for (Integer w : graph.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edges[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s ; x = edges[x]) {

            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
    public String getEdges() {
        return Arrays.toString(edges);
    }

    /**
     * 应该维护一个disTo[]数组，见BreadthFirstPaths类
     * @param v
     * @return
     */
    public int distTo(int v) {

        int count = 0;
        for(int x = v; x != s; x = edges[x]) {
            count++;
        }
        return count;
    }
    public static void main(String[] args) {

        MyGraph myGraph = new MyGraph(6);
        myGraph.addEdge(0, 2);
        myGraph.addEdge(1, 2);
//        myGraph.addEdge(3, 2);
        myGraph.addEdge(5, 4);
        myGraph.addEdge(5, 3);
        MyBreadthFirstPaths myBreadthFirstPaths = new MyBreadthFirstPaths(myGraph, 0);
        System.out.println(myBreadthFirstPaths.getEdges());
        for (int i = 0; i < myGraph.V(); i++) {
            System.out.print("0 - " + i + ": ");
            if (myBreadthFirstPaths.hasPathTo(i)) {
                for (Integer x : myBreadthFirstPaths.pathTo(i)) {
                    if (x == 0) System.out.print(x);
                    else System.out.print("-" + x);
                }
            }
            System.out.println();
        }
        System.out.println(myBreadthFirstPaths.distTo(1));
        System.out.println(myBreadthFirstPaths.distTo(2));
    }
}
