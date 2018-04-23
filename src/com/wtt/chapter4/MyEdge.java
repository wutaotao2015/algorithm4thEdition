package com.wtt.chapter4;

/**
 * 2018/4/23 13:41 add by wutaotao
 */
public class MyEdge implements Comparable<MyEdge> {

    private final int v;
    private final int w;
    private final double weight;

    public MyEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public int compareTo(MyEdge that) {

        if (this.weight < that.weight) return -1;
        if (this.weight > that.weight) return 1;
        return 0;
    }

    public double weight() {
        return this.weight;
    }
    public int either() {
        return v;
    }
    public int other(int vertex){
        if (vertex == this.v) return this.w;
        if (vertex == this.w) return this.v;
        throw new ArithmeticException();
    }

    @Override
    public String toString() {
        return "MyEdge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
