package org.test;


import org.example.algorithms.QuickSort;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        QuickSort sorter = new QuickSort();
        sorter.sort(arr, metrics);

        assertArrayEquals(expected, arr);
        assertTrue(metrics.getMaxDepth() > 0);
    }

    @Test
    void testLargeRandomArray() {
        int n = 50_000;
        int[] arr = new Random().ints(n, -1_000_000, 1_000_000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        QuickSort sorter = new QuickSort();
        sorter.sort(arr, metrics);

        assertArrayEquals(expected, arr);

        int logn = (int) (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= 2 * logn + 20);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] expected = arr.clone();

        Metrics metrics = new Metrics();
        QuickSort sorter = new QuickSort();
        sorter.sort(arr, metrics);

        assertArrayEquals(expected, arr);
    }
}
