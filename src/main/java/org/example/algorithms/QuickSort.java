package org.example.algorithms;

import org.example.metrics.Metrics;
import org.example.utils.ArrayUtils;

public class QuickSort {
    private final Metrics metrics;

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private void quicksort(int[] arr, int left, int right) {
        while (left < right) {
            metrics.enterRecursion();

            int pivotPos = ArrayUtils.partition(arr, left, right, metrics);

            if ((pivotPos - 1 - left) < (right - pivotPos)) {
                if (left < pivotPos - 1) {
                    quicksort(arr, left, pivotPos - 1);
                }

                left = pivotPos;
            } else {
                if (pivotPos < right) {
                    quicksort(arr, pivotPos, right);
                }

                right = pivotPos - 1;
            }

            metrics.leaveRecursion();
        }
    }
}

