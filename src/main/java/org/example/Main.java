package org.example;

import org.example.algorithms.*;
import org.example.metrics.Metrics;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String algo = null;
        int n = 1000;
        String csv = "results.csv";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--algo":
                    algo = args[++i];
                    break;
                case "--n":
                    n = Integer.parseInt(args[++i]);
                    break;
                case "--csv":
                    csv = args[++i];
                    break;
            }
        }

        if (algo == null) {
            System.err.println("Usage: --algo mergesort|quicksort|select|closest --n SIZE [--csv FILE]");
            return;
        }

        Metrics metrics = new Metrics();
        long start = System.currentTimeMillis();
        Object result = null;

        switch (algo.toLowerCase()) {
            case "mergesort": {
                int[] arr = randomArray(n, 42);
                MergeSort.sort(arr, metrics);
                result = arr[0];
                break;
            }
            case "quicksort": {
                int[] arr = randomArray(n, 43);
                QuickSort.sort(arr, metrics);
                result = arr[0];
                break;
            }
            case "select": {
                int[] arr = randomArray(n, 44);
                int k = n / 2;
                Select selector = new Select();
                result = selector.select(arr, k, metrics);
                break;
            }
            case "closest": {
                ClosestPair.Point[] pts = ClosestPair.randomPoints(n, 45);
                result = ClosestPair.closest(pts, metrics);
                break;
            }
            default:
                System.err.println("Unknown algo: " + algo);
                return;
        }

        long time = System.currentTimeMillis() - start;


        System.out.printf("Algo=%s, n=%d, time=%dms, comparisons=%d, allocations=%d, depth=%d, result=%s%n",
                algo, n, time, metrics.getComparisons(), metrics.getAllocations(), metrics.getMaxDepth(), result);

        metrics.writeCSV(csv, algo, n, time);
    }

    private static int[] randomArray(int n, long seed) {
        Random rnd = new Random(seed);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(1_000_000);
        }
        return arr;
    }
}