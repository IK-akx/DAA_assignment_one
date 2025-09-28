package org.test;

import org.example.algorithms.Select;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Metrics metrics = new Metrics();
        Select selector = new Select();

        for (int k = 0; k < arr.length; k++) {
            int result = selector.select(arr.clone(), k, metrics);
            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testRandomArrays() {
        Random rnd = new Random();
        for (int t = 0; t < 100; t++) {
            int n = rnd.nextInt(100) + 10;
            int[] arr = rnd.ints(n, -1000, 1000).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rnd.nextInt(n);

            Metrics metrics = new Metrics();
            Select selector = new Select();

            int result = selector.select(arr.clone(), k, metrics);
            assertEquals(sorted[k], result);
        }
    }
}
