package com.wtt.chapter4;

/**
 * 2018/4/17 18:04 add by wutaotao
 */
public class DirectedDFS {

    private boolean[] marked;

    public DirectedDFS(MyDigraph digraph, int s) {
        marked = new boolean[digraph.V()];
        dfs(digraph, s);
    }
    private void dfs(MyDigraph digraph, int v) {
        marked[v] = true;
        for (Integer w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }
    public DirectedDFS(MyDigraph digraph, Iterable<Integer> source) {
        marked = new boolean[digraph.V()];
        for (Integer s : source) {
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }
    }
    public boolean marked(int v) {return marked[v];}
}
