package com.wtt.chapter4;

import com.wtt.chapter1.practice.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**无向图实现
 * 1. 邻接表  v^2的数组，边v-w则代表v,w处的值为true,若无该边则为false 所需空间太大
 * 2. 边的数组  一个数组，它的元素是一个Edge类，Edge类有2个int实例变量代表一条边的2个顶点，查找顶点v相邻的顶点集合所需时间太多
 * 3. 邻接表表示
 * 数组索引代表顶点，数组元素是该顶点相邻的所有其他顶点，由bag实现（在链表头部进行操作的链表）
 * 此种实现每插入一条边都要对领接表进行2次操作
 *  性能复杂度：
 *      所需空间 ： E + V
 *      添加一条边：1
 *      检查v和w是否相邻： degree(v) v的相邻顶点个数
 *      遍历v的所有相邻节点： degree(v)
 *
 *  用红黑树代替数组，Set集合代替Bag的实现被称为邻接集，因为删除顶点和边，以及判断某条边是否在图中的操作较少，并且增加算法复杂度，故一般不采用
 *
 * 2018/4/10 15:05 add by wutaotao
 */
public class MyGraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public MyGraph(int v) {
        V = v;
        E = 0;
        adj = (Bag<Integer>[])new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    public MyGraph(In in){
        V = in.readInt();
        E = in.readInt();
        for (int i = 0; i < E; i++) {
            int a = in.readInt();
            int b = in.readInt();
            addEdge(a, b);
        }
    }

    public MyGraph(MyGraph graph) {
        // 调用顶点个数构造器完成v，e, adj变量的初始化工作，再赋值即可
        this(graph.V());
        this.E = graph.E();
        for (int v = 0; v < graph.V(); v++) {
            Stack<Integer> stack = new Stack<>();
            for (Integer w : graph.adj(v)) {
                stack.push(w);
            }
            // 背包是无序的
            for (Integer w : stack) {
                adj[v].add(w);
            }
        }
    }

    public int V() {return V;}
    public int E() {return E;}
    public void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a);
        E++;
    }
    // 与v相邻的所有顶点
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

}
