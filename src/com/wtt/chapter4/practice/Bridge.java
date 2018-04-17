package com.wtt.chapter4.practice;

/**
 * 2018/4/16 15:07 add by wutaotao
 */

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac Bridge.java
 *  Execution:    java  Bridge V E
 *  Dependencies: Graph.java GraphGenerator.java
 *
 *  Identifies bridge edges and prints them out. This decomposes
 *  a directed graph into two-edge connected components.
 *  Runs in O(E + V) time.
 *
 *  Key quantity:  low[v] = minimum DFS preorder number of v
 *  and the set of vertices w for which there is a back edge (x, w)
 *  with x a descendant of v and w an ancestor of v.
 *
 *  Note: code assumes no parallel edges, e.g., two parallel edges
 *  would be (incorrectly) identified as bridges.
 *
 ******************************************************************************/

public class Bridge {
    private int bridges;      // number of bridges
    private int cnt;          // counter
    private int[] pre;        // pre[v] = order in which dfs examines v
    private int[] low;        // low[v] = lowest preorder of any vertex connected to v

    public Bridge(Graph G) {
        low = new int[G.V()];
        pre = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            low[v] = -1;
        for (int v = 0; v < G.V(); v++)
            pre[v] = -1;

        for (int v = 0; v < G.V(); v++)
            if (pre[v] == -1)
                dfs(G, v, v);
    }

    public int components() {
        return bridges + 1;
    }

    private void dfs(Graph G, int u, int v) {
        pre[v] = cnt++;
        low[v] = pre[v];
        for (int w : G.adj(v)) {
            if (pre[w] == -1) {
                dfs(G, v, w);
                //这里是递归栈出栈后继续执行的部分，即继续执行上一层
                //这里继续将low[v]值取v,w中较小值，从而保证low[v]始终存储的是连通的最小值
                low[v] = Math.min(low[v], low[w]);
                // 若不成环，则下方的low[v]不会执行，即这里的w的low和pre值仍相等，即为线性，不成环，是桥
                if (low[w] == pre[w]) {
                    StdOut.println(v + "-" + w + " is a bridge");
                    bridges++;
                }
            }

            // update low number - ignore reverse of edge leading to v
            else if (w != u)
                // 这里是取v的相邻顶点中最小的low值，
                // 若u,v,w成环，则可以确定low[v]的值必定会取pre[w]值
                low[v] = Math.min(low[v], pre[w]);
        }
    }

    public String getPre() {
        return Arrays.toString(pre);
    }

    public String getLow() {
        return Arrays.toString(low);
    }

    // test client
    public static void main(String[] args) {
//        int V = Integer.parseInt(args[0]);
//        int E = Integer.parseInt(args[1]);
//        int V = 10;
//        int E = 7;
//        Graph G = GraphGenerator.simple(V, E);
        Graph G = new Graph(6);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(3, 5);
        G.addEdge(4, 5);
        StdOut.println(G);

        Bridge bridge = new Bridge(G);
        StdOut.println("Edge connected components = " + bridge.components());
        System.out.println("pre: " + bridge.getPre());
        System.out.println("low: " + bridge.getLow());
    }
/**
 * 只有u,v的值代表进入一次for(int w: G.adj(v))循环

 u  v  w
 0  0
 0  0  2  入栈
 0  2
 0  2  3  入栈
 2  3
 2  3  5  入栈
 3  5
 3  5  4  入栈
 5  4
 5  4  5   到这一步的时候pre[5] == -1条件不成立，u == w是回路
 5  4  3   4的第2个相邻顶点，w!=u,判断出5,4,3是在一个环内
 因为dfs递归方法都是对v的pre，low进行赋值，若pre[w] ！= -1，
 说明该w点之前已经被标记过了
 low[v] = 4, pre[w] = 2 （vw:43**成环**）
 5  4      上面4的相邻顶点已经遍历完成，即G.adj(v)遍历完，dfs返回后接着执行后面的命令
 即从这里开始返回执行递归栈里的方法,沿着深度优先搜索路径返回,回到上一步的354
 3  5  4	  出栈，比较5,4的low值，取小值
 low[v] = 3, low[w] = 2  (5,4)
 接着判断w = 4的low值和pre值是否相同，若相同则v-w是桥
 low[w] = 2, pre[w] = 4
 3  5      回到3,5对，以上是5的相邻顶点4（v=5,w=4)的处理过程，它已经全部执行完毕，
 进入循环的下次迭代，5的另一个相邻顶点3
 3  5  3   u == w是回路
 3  5      5的相邻边也执行完了，继续执行递归后235的代码
 2  3  5   出栈，low[v] = 2, low[w] = 2,pre[w] = 3 （3,5）
 2  3
 2  3  4   low[v] = 2, pre[w] = 4 （vw:34**成环**）
 2  3
 2  3  2   u == w是回路
 2  3      2,3完成，继续执行递归后023的代码
 0  2  3   出栈，low[v] = 1, low[w] = 2,pre[w] = 2 （2,3） 发现3是桥
 0  2
 0  2  1  入栈，pre[1] == -1成立，继续递归入栈
 2  1
 2  1  2  u == w
 2  1
 2  1  0  lov[v] = 5, pre[w] = 0 (1,0成环)
 2  1
 0  2  1  出栈，继续执行021，low[v] = 1, low[w] = 0,pre[w] = 5
 0  2
 0  2  0  u == w
 0  2
 0  0  2  出栈，low[v] = 0, low[w] = 0, pre[w] = 1
 0  0
 0  0  1  0的相邻顶点1， low[v] = 0, pre[w] = 5(0,1)
 0  0
 回到构造器发现所有顶点pre值都不为-1，bridge对象构造完毕

 */

}
