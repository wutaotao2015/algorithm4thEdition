package com.wtt.chapter4;

/**
 * 平方级别的查找一个顶点对另一个顶点的可达性
 * 传递闭包矩阵的第v行就是DirectedDFS数组的第v个元素DirectedDFS对象的marked数组
 *  对于大型的有向图更快的算法仍在研究中
 *
 * 2018/4/19 19:03 add by wutaotao
 */
public class MyTransitiveClosure {

    private DirectedDFS[] all;

    public MyTransitiveClosure(MyDigraph digraph) {

        all = new DirectedDFS[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            all[i] = new DirectedDFS(digraph, i);
        }
    }
    public boolean reachable(int v, int w) {
        // all[v]是以v为起点的有向图深度搜索对象
        return all[v].marked(w);
    }
}
