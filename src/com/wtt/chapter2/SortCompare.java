package com.wtt.chapter2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class SortCompare {

    public static double time(String alg, Double[] a) {
        Stopwatch sw = new Stopwatch();
        if      (alg.equals("MyInsertion"))       MyInsertion.sort(a);
//        else if (alg.equals("InsertionX"))      InsertionX.sort(a);
//        else if (alg.equals("BinaryInsertion")) BinaryInsertion.sort(a);
        else if (alg.equals("MySelection"))       MySelection.sort(a);
//        else if (alg.equals("Bubble"))          Bubble.sort(a);
        else if (alg.equals("MyShell"))             MyShell.sort(a);
        else if (alg.equals("MyMerge"))           MyMerge.sort(a);
        else if (alg.equals("MyMergeX"))          MyMergeX.sort(a);
//        else if (alg.equals("MergeBU"))         MergeBU.sort(a);
        else if (alg.equals("MyQuick"))           MyQuick.sort(a);
//        else if (alg.equals("Quick3way"))       Quick3way.sort(a);
//        else if (alg.equals("QuickX"))          QuickX.sort(a);
        else if (alg.equals("MyHeap"))            MyHeap.sort(a);
        else if (alg.equals("System"))          Arrays.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return sw.elapsedTime();
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeRandomInput(String alg, int n, int trials)  {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
            total += time(alg, a);
        }
        return total;
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeSortedInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = 1.0 * i;
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
//        String alg1 = args[0];
//        String alg2 = args[1];
//        int n = Integer.parseInt(args[2]);
//        int trials = Integer.parseInt(args[3]);
//        String alg1 = "MyShell";
//        String alg1 = "MyMergeX";
        String alg1 = "MyQuick";
        String alg2 = "MyHeap";
//        String alg2 = "MyMerge";
//        String alg2 = "MyInsertion";
//        String alg2 = "MySelection";
//        String alg2 = "MyShell";
        int n = 10000;
        int trials = 1000;
        double time1, time2;
//        if (args.length == 5 && args[4].equals("sorted")) {
//            time1 = timeSortedInput(alg1, n, trials);   // Total for alg1.
//            time2 = timeSortedInput(alg2, n, trials);   // Total for alg2.
//        }
//        else {
            time1 = timeRandomInput(alg1, n, trials);   // Total for alg1.
            time2 = timeRandomInput(alg2, n, trials);   // Total for alg2.
//        }
        System.out.println(time1 + ", " + time2);
        StdOut.printf("For %d random Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", time2/time1, alg2);
    }
}
