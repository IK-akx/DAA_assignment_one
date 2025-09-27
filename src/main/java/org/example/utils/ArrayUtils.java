package org.example.utils;

import org.example.metrics.Metrics;

import java.util.Random;

public class ArrayUtils {
    private static final Random random = new Random();

    /** Swap 2 elements of array */
    public static void swap(int[] arr, int i, int j, Metrics metrics) {
        if (i == j) return; // guard
        metrics.incrementAllocations();
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /** Classic partition for QuickSort */
    public static int partition(int[] arr, int left, int right, Metrics metrics) {
        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];

        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) {
                metrics.incrementComparisons();
                i++;
            }
            metrics.incrementComparisons();

            while (arr[j] > pivot) {
                metrics.incrementComparisons();
                j--;
            }
            metrics.incrementComparisons();

            if (i <= j) {
                swap(arr, i, j, metrics);
                i++;
                j--;
            }
        }
        return i;
    }

    /** Fisher–Yates shuffle */
    public static void shuffle(int[] arr, Metrics metrics) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j, metrics);
        }
    }

    /** Guard */
    public static void checkBounds(int[] arr, int left, int right) {
        if (left < 0 || right >= arr.length || left > right) {
            throw new IllegalArgumentException("Invalid array bounds: [" + left + ", " + right + "]");
        }
    }
}
