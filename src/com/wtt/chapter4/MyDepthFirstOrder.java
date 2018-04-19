package com.wtt.chapter4;

import com.wtt.chapter1.practice.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2018/4/18 15:06 add by wutaotao
 */
public class MyDepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public MyDepthFirstOrder(MyDigraph digraph) {

        marked = new boolean[digraph.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) dfs(digraph, i);
        }
    }
    private void dfs(MyDigraph digraph, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (Integer w : digraph.adj(v)) {
            if (!marked[w]) dfs(digraph, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }
    public Iterable<Integer> pre() {
        return pre;
    }
    public Iterable<Integer> post() {
        return post;
    }
    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        MyDigraph G = new MyDigraph(7);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 1);
        G.addEdge(2, 4);
        G.addEdge(4, 5);
        G.addEdge(5, 6);
        G.addEdge(6, 4);

        MyDepthFirstOrder dfs = new MyDepthFirstOrder(G);

        StdOut.print("Preorder:  ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        StdOut.println();


    }
}
