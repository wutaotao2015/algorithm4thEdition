package com.wtt.chapter4;

/**
 *
 * 有向无环图的拓扑排序, 为有优先级限制的调度问题提供了解决方案
 *  深度优先搜索的逆后序即拓扑排序
 *  该点可由深度优先的递归调用特性证明可知
 *
 *  可以将对该命题的证明想象成一颗向下生长的根，相邻顶点即分叉，
 *  逆后序（从下向上）即可保证拓扑的性质：所有的有向边都是前面的顶点指向后面的顶点
 *
 * 2018/4/18 15:29 add by wutaotao
 */
public class MyTopologicalOrder {

    // 保存为实例对象，不用每次调用都执行一次深度优先搜索
    private Iterable<Integer> order;

    public MyTopologicalOrder(MyDigraph digraph) {

        MyDirectedCycle myDirectedCycle = new MyDirectedCycle(digraph);
        if (!myDirectedCycle.hasCycle()) {
            MyDepthFirstOrder myDepthFirstOrder = new MyDepthFirstOrder(digraph);
            order = myDepthFirstOrder.reversePost();
        }
    }
    public boolean isDAG() {
        return order == null;
    }
    public Iterable<Integer> order() {
        return order;
    }
}
