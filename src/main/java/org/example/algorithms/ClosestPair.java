package org.example.algorithms;

import org.example.metrics.Metrics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double closest(Point[] points, Metrics metrics) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        metrics.incrementAllocations();

        return closestRec(sortedByX, 0, sortedByX.length - 1, metrics);
    }

    private static double closestRec(Point[] points, int left, int right, Metrics metrics) {
        metrics.enterRecursion();

        if (right - left <= 3) {
            double minDist = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    minDist = Math.min(minDist, dist(points[i], points[j], metrics));
                }
            }
            Arrays.sort(points, left, right + 1, Comparator.comparingDouble(p -> p.y));
            metrics.leaveRecursion();
            return minDist;
        }

        int mid = (left + right) / 2;
        double midX = points[mid].x;

        double d1 = closestRec(points, left, mid, metrics);
        double d2 = closestRec(points, mid + 1, right, metrics);
        double d = Math.min(d1, d2);

        Point[] temp = new Point[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (points[i].y < points[j].y) {
                temp[k++] = points[i++];
            } else {
                temp[k++] = points[j++];
            }
        }
        while (i <= mid) temp[k++] = points[i++];
        while (j <= right) temp[k++] = points[j++];
        System.arraycopy(temp, 0, points, left, temp.length);


        Point[] strip = new Point[right - left + 1];
        int stripSize = 0;
        for (int idx = left; idx <= right; idx++) {
            if (Math.abs(points[idx].x - midX) < d) {
                strip[stripSize++] = points[idx];
            }
        }


        for (int p = 0; p < stripSize; p++) {
            for (int q = p + 1; q < stripSize && (strip[q].y - strip[p].y) < d; q++) {
                d = Math.min(d, dist(strip[p], strip[q], metrics));
            }
        }

        metrics.leaveRecursion();
        return d;
    }

    private static double dist(Point a, Point b, Metrics metrics) {
        metrics.incrementComparisons();
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }


    public static double bruteForce(Point[] points, Metrics metrics) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                min = Math.min(min, dist(points[i], points[j], metrics));
            }
        }
        return min;
    }


    public static Point[] randomPoints(int n, long seed) {
        Random rnd = new Random(seed);
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(rnd.nextDouble() * 10000, rnd.nextDouble() * 10000);
        }
        return pts;
    }
}
