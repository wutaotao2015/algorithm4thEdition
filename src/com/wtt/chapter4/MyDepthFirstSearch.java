package com.wtt.chapter4;

/**
 * 用例传递进来图形，以及起点s, 每个起点s都对应着一个MyDepthFirstSearch对象
 * 在构造器中即使用深度优先搜索算法完成所有连通顶点的标记工作。
 *
 * 深度优先搜索,必须指定一个搜索起点
 *
 * 2018/4/11 14:54 add by wutaotao
 */
public class MyDepthFirstSearch {

    // 邻接表的标记数组
    private boolean[] marked;
    // 与起点s连通的顶点个数
    private int count;

    public MyDepthFirstSearch(MyGraph graph, int s) {

        marked = new boolean[graph.V()];
        dfs(graph, s);
    }
    private void dfs(MyGraph graph, int v) {
        marked[v] = true;
        count++;
        for (int i : graph.adj(v)) {
            if (!marked[i]) dfs(graph, i);
        }
    }
    public boolean marked(int v) {
       return marked[v];
    }
    public int count(){
        return count;
    }
}
