package com.wtt.chapter4;

import java.util.Arrays;

/**
 * 寻找有向图的强连通分量的个数
 * 在无向图的连通分量个数MyCC的基础上修改即可
 * 使用depthFirstOrder的前序和后序遍历ids都是[0,0,0,0,0,0,0]
 * 说明按这2种顺序
 * <p>
 * Kosaraju算法正确性证明：
 * 这个算法的正确性由书中的证明可以完整得出，现将自己的理解归纳如下
 * <p>
 * G代表正向图，GR代表反向图
 * <p>
 * 1.证明所有与s的强连通性的顶点一定会被Kosaraju构造函数调用的dfs(G,s)访问到
 * 这是一个子集和全集的区别
 * 由反证法可以得到，假设一个强连通点v不被Kosaraju构造函数调用的dfs(G,s)访问，
 * 因为存在s到v的路径，根据深度优先搜索路径，如果想不访问v（不调用dfs(G,v)),
 * 只能是v之前已经被标记过了（marked(v) == true），
 * 而标记v是通过dfs(G,v)来标记的,即dfs(G,v)在dfs(G,s)之前被调用了，
 * 而v与s强连通，存在v到s的路径，所以在dfs(G,v)中s肯定会被标记，
 * 而回到Kosaraju构造器中进入下一个循环，Kosaraju构造器肯定不会再去调用dfs(G,s)了，从而得到矛盾！
 * <p>
 * （由以上证明可以得出，Kosaraju构造器中循环遍历顶点时，
 * 每个循环调用的dfs(G,s)访问的顶点集合中包含了所有
 * 与s强连通的顶点集）
 * <p>
 * 2. 证明Kosaraju构造函数调用的dfs(G,s)访问的每个顶点v都是和s强连通的
 * 即证明1中的子集就是强连通的全集，即子集==全集
 * <p>
 * 设顶点v是Kosaraju构造函数调用的dfs(G,s)访问到的一个顶点，这说明在
 * 正向图G中存在一条s到v的路径，要证明强连通性只需证明G中仍存在一条v到s的路径，
 * 等价于反向图GR中存在s到v的路径。
 * <p>
 * 首先，
 * **论证***
 * 在GR的深度优先搜索中，dfs(GR, v)必然在dfs(GR, s)之前就结束了
 * 1. 在还没有调用dfs(GR,s)之前结束(v不可达s)
 * 2. 在结束调用dfs(GR,s)前结束（存在s->v)
 * **
 * G中存在s到v的路径，意味着GR中存在着一条v到s的路径，这样就破除了1，只有2才可以，得证。
 * 但是，为什么dfs(GR, v)一定会在dfs(GR, s)之前结束？这和GR的逆后序有什么关系？
 * ##########################
 * 逆后序--> 出栈顺序 --> dfs调用顺序
 * ##########################
 * 因为逆后序的定义即是前面的顶点指向后面的顶点，则Kosaraju构造函数调用的dfs(G,s)访问的每个顶点v，
 * 也符合拓扑排序sv,符合出入栈顺序，即v先入栈，s后入栈，所以dfs(GR, v)必然在dfs(GR, s)之前就结束了。
 * ********
 * 总之，反向图的逆后序保证了v->s的可达性，（虽然由于环的存在，拓扑的完整约束（全部向后）不能保证，但按顶点向后遍历的可达性可以得到保证)
 * 在此基础上再对正向图进行深度优先搜索则保证了s->v的可达性，一正一反即可保证强连通性！！！
 * 这样通过2个单向保证可达的线索，可以得到需要的有向图的强连通分量。
 * <p>
 * ###########################
 * PS: 即任意的sv的拓扑排序也是对应着以上2种情况，要么v,s没有可达性(v不可达s)，调完dfs(v)后回到构造器调dfs(s),拓扑顺序为sv；
 * 要么v,s是强连通的,存在v->s：先调dfs(v),在递归调dfs(s),然后dfs(v)先执行完，v先入栈，
 * 再执行完dfs(s)，s后入栈，拓扑顺序也为sv.
 * <p>
 * 从这里来看，一副有向图的拓扑排序原来用于处理有向无环图的优先级限制的调度问题，
 * 当处理的图中存在强连通性，即有环时，拓扑排序（逆后序）提供的排序是这样的：
 * 如顺序为vws，则vw,ws要么是单向可达(v->w, w->s)，要么是强连通的，
 * 这么久才发现这是废话！！！！！！
 * <p>
 * ****实例观察******
 * （其逆后序结果4 6 5 1 3 2 0,前面指向后面的顶点，在GR图中看，
 * 即前面的环指向后面的环，这里的环即是强连通的顶点集。
 * 此时用这个逆后序来遍历正向图，即可以发现由于方向是相反的，当调用环中的第一个顶点时，
 * 第二个环中的顶点不会被递归访问到，从而实现了环（强连通分量）的区分）
 * ***
 * <p>
 * 为什么要用反向图的逆后序，而不能用原图的后序？
 * <p>
 * 这个问题的严格证明需要比较好的数学知识，本人数学水平有限，只能定性的回答一下。
 * 终端研发部的回答将书上的正确性证明没有讲清楚的地方补充完整了，是非常正确的。
 * Kosaraju算法的关键个人认为还是对强连通性的双向可达性的证明，比如dfs(s)调用过程中访问到了v顶点，
 * 这样深度优先搜索隐式的提供了s->v的可达性，而只有逆后序（拓扑排序）才能提供
 * v->s的可达性，而后序是无法保证这种单向可达性的，可以多测下后序的例子观察这一点，所以从可达性的角度来看，
 * 只有逆后序才能提供这点。而原图的逆后序显然不能用于第2次遍历原图，所以只有反向图的逆后序才是正解。
 * <p>
 * 从网上又找到个资料，可以从另一个角度来回答这个问题，因为算法的最终目的是找出所有的独立环，
 * 那么最后的结果肯定是一个个独立的环，如果把所有的环收缩成一个顶点，那么该图会成为一个有向无环图，
 * 所以用整体的看法来看这个问题，我们就应该用拓扑排序来处理图。而通过反向图我们可以先拿到原图中后面的独立环，
 * 因为后面的独立环对于前面的独立环是不可达的（拓扑排序性质保证），这样就可以通过遍历拓扑排序拿到独立的强连通环了。
 * <p>
 * <p>
 * 2018/4/19 9:06 add by wutaotao
 */
public class MyKosaraju {

    private boolean[] marked;
    private int[] ids;
    private int count;

    public MyKosaraju(MyDigraph digraph) {
        marked = new boolean[digraph.V()];
        ids = new int[digraph.V()];
        MyDepthFirstOrder depthFirstOrder = new MyDepthFirstOrder(digraph);
        for (Integer v : depthFirstOrder.post()) {
            System.out.print(v + " ");
            if (!marked[v]) {
                dfs(digraph, v);
                count++;
            }
        }
    }

    private void dfs(MyDigraph digraph, int v) {
        marked[v] = true;
        ids[v] = count;
        for (Integer w : digraph.adj(v)) {
            if (!marked[w]) dfs(digraph, w);
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return ids[v];
    }

    public String getIds() {
        return Arrays.toString(ids);
    }

    public static void main(String[] args) {

        MyDigraph myDigraph = new MyDigraph(5);
        myDigraph.addEdge(0, 1);
        myDigraph.addEdge(1, 2);
        myDigraph.addEdge(2, 3);
        myDigraph.addEdge(3, 1);
        myDigraph.addEdge(2, 4);
        myDigraph.addEdge(4, 6);
        myDigraph.addEdge(6, 5);
        myDigraph.addEdge(5, 4);
        MyKosaraju myKosaraju = new MyKosaraju(myDigraph);
        System.out.println(" begin the strongConnection count: ");
        System.out.println(myKosaraju.count());
        System.out.println(myKosaraju.id(2));
        System.out.println(myKosaraju.id(4));
        System.out.println(myKosaraju.getIds());
    }
}
