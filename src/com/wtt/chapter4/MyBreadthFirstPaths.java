package com.wtt.chapter4;

import com.wtt.chapter1.practice.Queue;

import java.util.Stack;

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
}
