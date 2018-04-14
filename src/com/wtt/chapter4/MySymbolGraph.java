package com.wtt.chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 符号图
 *
 * 顶点为字符串
 *
 * 用一个符号表st,反向索引数组ids,无向图mygraph 3种数据结构来实现其api
 *
 * indexOf(), nameOf()2个方法处理顶点的字符串表示和实际在图实现中顶点的整数值
 *
 * Created by wutaotao
 * 2018/4/14 19:45
 */
public class MySymbolGraph {

    private ST<String, Integer> st;
    private String[] ids;
    private MyGraph graph;

    public MySymbolGraph(String file, String delim) {

        st = new ST<>();
        In in = new In(file);
        // 构造符号表
        while(in.hasNextLine()) {
            String[] vs = in.readLine().split(delim);
            for (String v : vs) {
                if (!st.contains(v)) {
                    // 每个顶点对应索引由符号表大小确定，从而实现动态增长
                    st.put(v, st.size());
                }
            }
        }
        // 构造反向索引数组
        ids = new String[st.size()];
        for (String name : st.keys()) {
            ids[st.get(name)] = name;
        }
        // 构造无向图
        graph = new MyGraph(st.size());
        in = new In(file);
        while (in.hasNextLine()) {

            String[] vs = in.readLine().split(delim);
            // 取得第一个顶点，作为边的一端，不可放入循环中，导致多次调用get方法
            int v = st.get(vs[0]);
            for (int i = 1; i < vs.length; i++) {
                graph.addEdge(v, st.get(vs[i]));
            }
        }
    }
    public boolean contains(String name) {
        return st.contains(name);
    }
    public String nameOf(int id) {
        return ids[id];
    }
    public int indexOf(String name) {
        return st.get(name);
    }
    public MyGraph graph() {
        return graph;
    }

    /**
     *  该用例实现了输入一个字符串的顶点，用例打印出图中与其相邻的顶点字符串
     *
     * @param args
     */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        MySymbolGraph sg = new MySymbolGraph(filename, delimiter);
        MyGraph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.nameOf(v));
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
}
