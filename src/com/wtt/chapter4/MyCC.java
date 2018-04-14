package com.wtt.chapter4;

import java.util.Arrays;

/**
 * 深度优先搜索实现的连通性查询
 * 由于该查询必须对图进行预处理，通过递归将ids中每个顶点的分量全部查询出来后才能进行联通性判断
 * 所以如果是单纯的联通性查询或查询与插入操作混合时，unionFind方法更为高效
 *
 * Created by wutaotao
 * 2018/4/14 07:34
 */
public class MyCC {

    private boolean[] marked;
    private int[] ids;
    private int count;

    public MyCC(MyGraph graph) {

        int vNum = graph.V();
        marked = new boolean[vNum];
        ids = new int[vNum];
        for (int i = 0; i < vNum; i++) {

            if (!marked[i]) {
                dfs(graph, i);
                // 第一个联通分量从0开始,每个分量标记完成后将联通数加一
                count++;
            }
        }
    }
    private void dfs(MyGraph graph, int v) {

        marked[v] = true;
        ids[v] = count;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }
    public boolean connected(int v, int w) {
        return ids[v] == ids[w];
    }
    public int count() {
        return count;
    }
    public int id(int v) {
        return ids[v];
    }
    public int[] getIds() {
        return ids;
    }

    public static void main(String[] args) {
        MyGraph myGraph = new MyGraph(6);
        myGraph.addEdge(0, 2);
        myGraph.addEdge(1, 2);
//        myGraph.addEdge(3, 2);
        myGraph.addEdge(5, 4);
        myGraph.addEdge(5, 3);
        MyCC myCC = new MyCC(myGraph);
        System.out.println(Arrays.toString(myCC.getIds()));
        System.out.println(myCC.count());
    }
}
