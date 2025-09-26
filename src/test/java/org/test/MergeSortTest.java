package org.test;

import org.example.algorithms.MergeSort;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MergeSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 3};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
        assertTrue(metrics.getComparisons() > 0);
    }

    @Test
    void testRandomLargeArray() {
        Random rand = new Random(42);
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) arr[i] = rand.nextInt();

        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
        assertTrue(metrics.getMaxDepth() > 0 && metrics.getMaxDepth() <= 32); // ~ log2(10000)
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] expected = arr.clone();

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
    }
}
