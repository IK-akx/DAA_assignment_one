package org.example.algorithms;

import org.example.metrics.Metrics;

public class MergeSort {

    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics metrics) {
        int[] temp = new int[arr.length];
        metrics.incrementAllocations();
        mergeSort(arr, temp, 0, arr.length - 1, metrics);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right, Metrics metrics) {
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }

        metrics.enterRecursion();
        int mid = left + (right - left) / 2;

        mergeSort(arr, temp, left, mid, metrics);
        mergeSort(arr, temp, mid + 1, right, metrics);

        merge(arr, temp, left, mid, right, metrics);
        metrics.leaveRecursion();
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, Metrics metrics) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }
}
