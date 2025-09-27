package org.test;

import org.example.metrics.Metrics;
import org.example.algorithms.ClosestPair;
import org.example.algorithms.ClosestPair.Point;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ClosestPairTest {

    @Test
    void testSmallRandom() {
        Metrics m1 = new Metrics();
        Metrics m2 = new Metrics();

        for (int n = 2; n <= 200; n++) {
            Point[] pts = ClosestPair.randomPoints(n, 1234 + n);

            double d1 = ClosestPair.closest(pts.clone(), m1);
            double d2 = ClosestPair.bruteForce(pts.clone(), m2);

            assertEquals(d2, d1, 1e-6, "n=" + n);
        }
    }

    @Test
    void testCompareWithBruteForceUpTo2000() {
        for (int n = 50; n <= 2000; n += 200) {
            Point[] pts = ClosestPair.randomPoints(n, n);

            Metrics m1 = new Metrics();
            Metrics m2 = new Metrics();

            double d1 = ClosestPair.closest(pts.clone(), m1);
            double d2 = ClosestPair.bruteForce(pts.clone(), m2);

            assertEquals(d2, d1, 1e-6, "Mismatch at n=" + n);
        }
    }
}
