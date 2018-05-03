package com.wtt.chapter4;

/**
 * 有向图的边
 * 2018/4/27 16:12 add by wutaotao
 */
public class MyDirectedEdge {

    private final int v;
    private final int w;
    private final double weight;

    public MyDirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }
    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }
    @Override
    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
