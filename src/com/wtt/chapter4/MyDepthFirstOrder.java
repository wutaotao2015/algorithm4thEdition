package com.wtt.chapter4;

import com.wtt.chapter1.practice.Queue;
import edu.princeton.cs.algs4.Stack;

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
    public Iterable<Integer> preOrder() {
        return pre;
    }
    public Iterable<Integer> postOrder() {
        return post;
    }
    public Iterable<Integer> reversePostOrder() {
        return reversePost;
    }
}
