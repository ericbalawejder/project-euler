package net.euler.project.fifty;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.LongStream;

/**
 * TODO: Use prime factorization to determine the number of factors for any N.
 * product of powers + 1
 */
public class Problem12HighlyDivisibleTriangularNumber {

    public static void main(String[] args) {
        System.out.println(findTriangleNumberWithNDivisors(500));
    }

    static long findTriangleNumberWithNDivisors(int numberOfDivisors) {
        return LongStream.iterate(1, i -> i + 1)
                .map(Problem12HighlyDivisibleTriangularNumber::computeTriangleNumber)
                .filter(t -> findFactors(t).size() > numberOfDivisors)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("triangle number not found"));
    }

    private static long computeTriangleNumber(long n) {
        return  (n * (n + 1)) / 2;
    }

    private static SortedSet<Long> findFactors(long n) {
        final SortedSet<Long> factors = new TreeSet<>();
        for (long i = 1; i <= (long) Math.sqrt(n); i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }
        return Collections.unmodifiableSortedSet(factors);
    }

}
