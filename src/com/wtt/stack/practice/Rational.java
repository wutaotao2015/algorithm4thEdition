package com.wtt.stack.practice;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Rational implements Comparable<Rational>{

    private static Rational ZERO = new Rational(0, 1);
    private long numerator;
    private long denominator;

    /**
     * 在构造器中进行分母为0判断，并进行分数约分操作
     * @param numerator
     * @param denominator
     */
    public Rational(long numerator, long denominator) {

        if (denominator == 0) throw new IllegalArgumentException("the denominator is 0");

        long g = gcd(numerator, denominator);
        this.numerator = numerator/g;
        this.denominator = denominator/g;
        if (this.denominator < 0){
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }

    /**
     * greatest common divisor
     * 最大公约数 返回正数
     * @param p
     * @param q
     * @return
     */
    private long gcd(long p, long q){
        if (p < 0) p = -p;
        if (q < 0) q = -q;
        if (q == 0) return p;
        return gcd(q, p%q);
    }

    /**least common multiple
     * 最小公倍数 返回正数
     * @return
     */
    public long lcm(long p, long q) {
        if (p < 0) p = -p;
        if (q < 0) q = -q;
        return p * (q / gcd(p, q));
    }

    public Rational plus(Rational b) {
        if (ZERO.equals(b)) return this;
        if (ZERO.equals(this)) return b;

        long numGcd = gcd(numerator, b.numerator);
        long denGcd = gcd(denominator, b.denominator);

        Rational res = new Rational((numerator/numGcd) * (b.denominator/denGcd)
                    + (b.numerator/numGcd) * (denominator/denGcd),
                denominator * (b.denominator / denGcd));
        res.numerator *= numGcd;
        return res;
    }
    public Rational negate(){
        return new Rational(-numerator, denominator);
    }
    public Rational minus(Rational b) {
        return plus(b.negate());
    }

    /**
     * 2个乘数已经互质，进行约分后再分子相城，分母相乘
     * @param b
     * @return
     */
    public Rational times(Rational b) {
        Rational c = new Rational(this.numerator, b.denominator);
        Rational d = new Rational(b.numerator, this.denominator);
        return new Rational(c.numerator * d.numerator, c.denominator * d.denominator);
    }
    public Rational reciprocal(){
        return new Rational(denominator, numerator);
    }
    public Rational divides(Rational b) {
        return this.times(b.reciprocal());
    }
    @Override
    public int compareTo(Rational o) {
        long lm = numerator * o.denominator;
        long rm = o.numerator * denominator;
        if (lm > rm) return +1;
        if (lm < rm) return -1;
        return 0;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Rational)) return false;
        Rational that = (Rational)obj;
        return this.compareTo(that) == 0;
    }

    @Override
    public String toString() {
        if (this.denominator == 1) return this.numerator + "";
        return this.numerator + "/" + this.denominator;
    }

    public static void main(String[] args) {

        Rational a = new Rational(1, 3);
        Rational b = new Rational(0, 4);
        Rational plus = a.plus(b);
        StdOut.println(a + " + " + b + " = " + plus);

        Rational minus = a.minus(b);
        StdOut.println(a + " - " + b + " = " + minus);

        Rational times = a.times(b);
        StdOut.println(a + " * " + b + " = " + times);

//        Rational divedes = a.divides(b);
//        StdOut.println(a + " / " + b + " = " + divedes);

        Rational x, y, z;

        // 1/2 + 1/3 = 5/6
        x = new Rational(1, 2);
        y = new Rational(1, 3);
        z = x.plus(y);
        StdOut.println(z);

        // 8/9 + 1/9 = 1
        x = new Rational(8, 9);
        y = new Rational(1, 9);
        z = x.plus(y);
        StdOut.println(z);

        // 1/200000000 + 1/300000000 = 1/120000000
        x = new Rational(1, 200000000);
        y = new Rational(1, 300000000);
        z = x.plus(y);
        StdOut.println(z);

        // 1073741789/20 + 1073741789/30 = 1073741789/12
        x = new Rational(1073741789, 20);
        y = new Rational(1073741789, 30);
        z = x.plus(y);
        StdOut.println(z);

        //  4/17 * 17/4 = 1
        x = new Rational(4, 17);
        y = new Rational(17, 4);
        z = x.times(y);
        StdOut.println(z);

        // 3037141/3247033 * 3037547/3246599 = 841/961
        x = new Rational(3037141, 3247033);
        y = new Rational(3037547, 3246599);
        z = x.times(y);
        StdOut.println(z);

        // 1/6 - -4/-8 = -1/3
        x = new Rational(1, 6);
        y = new Rational(-4, -8);
        z = x.minus(y);
        StdOut.println(z);
    }

}
