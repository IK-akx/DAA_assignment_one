package org.example.algorithms;

import org.example.metrics.Metrics;
import org.example.utils.ArrayUtils;

import java.util.Arrays;

public class Select {
    private final Metrics metrics;

    public Select(Metrics metrics) {
        this.metrics = metrics;
    }


    public int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private int select(int[] arr, int left, int right, int k) {
        metrics.enterRecursion();

        while (true) {
            if (left == right) {
                metrics.leaveRecursion();
                return arr[left];
            }

            int pivot = medianOfMedians(arr, left, right);

            int pivotIndex = partition(arr, left, right, pivot);

            if (k == pivotIndex) {
                metrics.leaveRecursion();
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        return medianOfMedians(medians, 0, medians.length - 1);
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        int i = left;
        int j = right;

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
                ArrayUtils.swap(arr, i, j, metrics);
                i++;
                j--;
            }
        }
        return j;
    }
}
