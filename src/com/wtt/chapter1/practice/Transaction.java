package com.wtt.chapter1.practice;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Transaction implements Comparable<Transaction>{

    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String transaction) {
        String[] a = transaction.split("\\s+");
        who = a[0];
        when = new Date(a[1]);
        amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount)) throw new IllegalArgumentException("Amount can't be NaN or infinite");
    }

    public String who(){return who;}
    public Date when(){return when;}
    public double amount(){return amount;}

    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Transaction)) {return false;}
        Transaction that = (Transaction)obj;
        return (this.who.equals(that.who)) && (this.when.equals(that.when)) && (this.amount == that.amount);
    }

    @Override
    public int compareTo(Transaction o) {
        return Double.compare(this.amount, o.amount);
    }

    public static class WhoOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.who.compareTo(o2.who);
        }
    }
    public static class WhenOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.when.compareTo(o2.when);
        }
    }
    public static class HowMuchOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return Double.compare(o1.amount, o2.amount);
        }
    }
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + this.who.hashCode();
        hash = hash * 31 + this.when.hashCode();
        hash = hash * 31 + ((Double)this.amount).hashCode();
        return hash;
    }
    public static void main(String[] args) {
        Transaction[] a = new Transaction[4];
        a[0] = new Transaction("Turing   6/17/1990  644.08");
        a[1] = new Transaction("Tarjan   3/26/2002 4121.85");
        a[2] = new Transaction("Knuth    6/14/1999  288.34");
        a[3] = new Transaction("Dijkstra 8/22/2007 2678.40");

        StdOut.println("Unsorted");
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by date");
        Arrays.sort(a, new Transaction.WhenOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by customer");
        Arrays.sort(a, new Transaction.WhoOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by amount");
        Arrays.sort(a, new Transaction.HowMuchOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
    }
}
