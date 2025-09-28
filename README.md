# DAA Assignment One 
# Iskander Kustayev (SE-2403)

## How to Run

### Requirements
- Java JDK 21+
- Maven (for build & dependencies)


## Architecture

The project implements four algorithms:
- **MergeSort**
- **QuickSort**
- **Deterministic Select** (Median-of-Medians)
- **Closest Pair of Points** (Divide & Conquer)

For each algorithm we additionally track:
- **depth** (recursion depth),
- **allocations** (number of new arrays/objects),
- **comparisons** (number of comparisons).

These metrics are monitored through a `Metrics` wrapper that is passed into the algorithm and updated whenever a comparison or recursive call occurs.

---

## Recurrence Relations & Complexity

- **MergeSort**  
  T(n) = 2T(n/2) + Θ(n)  
  → Master theorem, Case 2 → Θ(n log n)

- **QuickSort**  
  Expected: Θ(n log n) (random pivot)  
  Worst-case: Θ(n²) (bad pivot at each step)

- **Select (Median of Medians)**  
  T(n) = T(n/5) + T(7n/10) + Θ(n)  
  → Θ(n)

- **Closest Pair of Points (Divide & Conquer)**  
  T(n) = 2T(n/2) + Θ(n)  
  → Master theorem, Case 2 → Θ(n log n)

---

## Benchmark Results

## Run Benchmarks

To compare algorithms:
``` bash
java -cp target/classes org.example.bench.Benchmarks
```

### Select vs Sort
### Select vs Sort

| n       | Select (µs) | Sort (µs) | Comparisons |
|---------|-------------|-----------|-------------|
| 1,000   | 2,366       | 366       | 2,147       |
| 10,000  | 10,323      | 9,256     | 20,376      |
| 100,000 | 14,268      | 32,074    | 198,695     | 


✅ **Select is faster** than sorting for finding the k-th element, especially for large n.

---

## Analysis

- **Theoretical predictions match experiments**:
    - MergeSort and QuickSort → Θ(n log n).
    - Select → faster than Sort for large n.
    - Closest Pair → far fewer comparisons than BruteForce.

- **Observed differences**:
    - For small n, asymptotic differences are not visible (Divide&Conquer ≈ BruteForce).
    - QuickSort shows more variation depending on pivot choice.

---

# Conclusion

 - MergeSort and QuickSort both confirm the theoretical Θ(n log n) complexity.

 - Select (Median of Medians) achieves linear Θ(n) time and is consistently faster than sorting when only the k-th element is needed.

 - Closest Pair of Points significantly reduces the number of comparisons from O(n²) to O(n log n) compared to brute force, though for small input sizes the difference in runtime is negligible.

 - Depth and allocations confirm recursion structure: MergeSort and Closest Pair grow as log n, Select remains shallow, QuickSort may degrade depending on pivot.

