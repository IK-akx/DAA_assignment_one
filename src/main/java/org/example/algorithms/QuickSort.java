package org.example.algorithms;

import org.example.metrics.Metrics;

import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random random = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private void quicksort(int[] arr, int left, int right) {
        while (left < right) {
            metrics.enterRecursion();

            // выбираем случайный пивот
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
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }


            if ((j - left) < (right - i)) {
                if (left < j) {
                    quicksort(arr, left, j);
                }
                left = i;
            } else {
                if (i < right) {
                    quicksort(arr, i, right);
                }
                right = j;
            }

            metrics.leaveRecursion();
        }
    }

    private void swap(int[] arr, int i, int j) {
        metrics.incrementAllocations();
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

