package org.example.bench;

import org.example.algorithms.ClosestPair;
import org.example.algorithms.Select;
import org.example.metrics.Metrics;

import java.util.Arrays;
import java.util.Random;

public class Benchmarks {

    public static void main(String[] args) {
        benchmarkSelectVsSort();
    }

    private static void benchmarkSelectVsSort() {
        Random rnd = new Random(42);
        int n = 1000;
        int[] arr = rnd.ints(n, 0, 1_000_000).toArray();
        int k = n / 2;

        // Select
        Metrics m1 = new Metrics();
        int[] arr1 = arr.clone();
        long t1 = System.nanoTime();
        int result1 = new Select().select(arr1, k, m1);
        t1 = System.nanoTime() - t1;

        // Sort
        Metrics m2 = new Metrics();
        int[] arr2 = arr.clone();
        long t2 = System.nanoTime();
        Arrays.sort(arr2);
        int result2 = arr2[k];
        t2 = System.nanoTime() - t2;

        System.out.println("--- Select vs Sort ---");
        System.out.printf("Select: result=%d, time=%d µs, comparisons=%d%n",
                result1, t1 / 1000, m1.getComparisons());
        System.out.printf("Sort:   result=%d, time=%d µs%n",
                result2, t2 / 1000);
        System.out.println();
    }
}
