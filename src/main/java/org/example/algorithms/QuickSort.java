package org.example.algorithms;

import org.example.metrics.Metrics;
import org.example.utils.ArrayUtils;

public class QuickSort {

    public static void sort(int[] arr, Metrics metrics) {
        quicksort(arr, 0, arr.length - 1, metrics);
    }

    private static void quicksort(int[] arr, int left, int right, Metrics metrics) {
        while (left < right) {
            metrics.enterRecursion();

            int pivotPos = ArrayUtils.partition(arr, left, right, metrics);

            if ((pivotPos - 1 - left) < (right - pivotPos)) {
                if (left < pivotPos - 1) {
                    quicksort(arr, left, pivotPos - 1, metrics);
                }
                left = pivotPos;
            } else {
                if (pivotPos < right) {
                    quicksort(arr, pivotPos, right, metrics);
                }
                right = pivotPos - 1;
            }

            metrics.leaveRecursion();
        }
    }
}
