package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    private long comparisons;
    private long allocations;
    private int currentDepth;
    private int maxDepth;

    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void addComparisons(long c) {
        comparisons += c;
    }

    public void incrementAllocations() {
        allocations++;
    }

    public void addAllocations(long a) {
        allocations += a;
    }


    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void leaveRecursion() {
        currentDepth--;
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
    }

    public void writeCSV(String filename, String algoName, int n, long timeMillis) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(String.format("%s,%d,%d,%d,%d,%d\n",
                    algoName, n, timeMillis, comparisons, allocations, maxDepth));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}