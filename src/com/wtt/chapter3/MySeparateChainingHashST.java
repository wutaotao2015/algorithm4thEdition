package com.wtt.chapter3;

import com.wtt.chapter1.practice.MyChainQueue;

/**
 *
 * 由于散列函数将键均匀的分布到m个位置中，原来的键的顺序已经消失了
 * 在键的顺序不重要的应用中，基于拉链法的散列表是最快的，也是最广泛的
 *
 * 2018/4/4 9:44 add by wutaotao
 */
public class MySeparateChainingHashST<Key, Val> {

    private MySequentialSearchST<Key, Val>[] st;
    // hashTable size 由于散列的均匀性，如果键够多，可以认为m个索引位置处都会有链条存在，而且长度平均
    private int m;
    // key-value pairs 键值对的总数
    private int n;

    public MySeparateChainingHashST() {
        this(2);
    }

    public MySeparateChainingHashST(int m) {

        this.m = m;
        st = (MySequentialSearchST<Key, Val>[])new MySequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new MySequentialSearchST<>();
        }
    }
    // 构造一个新的hashSt,遍历原有的hashSt,调用tmpST的put方法对每个键进行重新散列的过程，完后再把对应的属性赋给当前对象，从而实现升级数组
    private void resize(int newM) {
        MySeparateChainingHashST<Key, Val> tmpHashST = new MySeparateChainingHashST<>(newM);
        for (MySequentialSearchST<Key, Val> sequentialSearchST : st) {
            for (Key key : sequentialSearchST.keys()) {
                tmpHashST.put(key, sequentialSearchST.get(key));
            }
        }
        this.st = tmpHashST.st;
        this.m = tmpHashST.m;
        this.n = tmpHashST.n;
    }
    // 将键散列到m个链条中
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
    public void put(Key key, Val val) {
        // 原文是10，这里为了main方法看效果，取3
        // 如果键的总数大于2倍的m,意味着每条链条的平均长度超过了2,此时我们将数组长度扩展一倍
        if (n >= 2 * m) resize(2 * m);
        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, val);
    }
    // 键的散列值是符号表索引，该索引的元素值是链表
    public Val get(Key key) {
        return st[hash(key)].get(key);
    }
    public void delete(Key key) {

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        // 链条平均长度小于等于2
        if (m > 2 && n <= 2*m) resize(m / 2);
    }
    // keys方法返回Iterable接口即可以调用foreach
    public Iterable<Key> keys() {
        MyChainQueue<Key> queue = new MyChainQueue<>();
        for (MySequentialSearchST<Key, Val> sequentialSearchST : st) {
            for (Key key : sequentialSearchST.keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {

        // 这个例子可以看到重新hash引起键的位置重新排列的结果
        MySeparateChainingHashST<Integer, String> st = new MySeparateChainingHashST<>();
        st.put(3, "$");
        st.put(6, "$");
        st.put(9, "$");
        st.put(1, "$");
        for (Integer integer : st.keys()) {
            System.out.println(integer + " " +st.get(integer));
        }
        System.out.println("begin put others");
        st.put(2, "#");
        st.put(4, "#");
        st.put(5, "#");
        st.put(7, "#");
        for (Integer integer : st.keys()) {
            System.out.println(integer + " " +st.get(integer));
        }
        System.out.println();
    }
}
