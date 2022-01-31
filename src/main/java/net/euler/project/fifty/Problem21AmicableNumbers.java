package net.euler.project.fifty;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Optimize by finding the pairs and then skipping the values less than the second value in
 * the pair when counting to 10_000.
 */
public class Problem21AmicableNumbers {

    private static final AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println(sumAmicableNumbers(findAmicableNumbers(10_000)));
        System.out.println(atomicCounter);
    }

    static int sumAmicableNumbers(List<Integer> amicableNumbers) {
        return amicableNumbers.stream()
                .reduce(0, Integer::sum);
    }

    private static List<Integer> findAmicableNumbers(int limit) {
        return IntStream.range(0, limit)
                .filter(Problem21AmicableNumbers::isAmicableNumber)
                .boxed()
                .toList();
    }

    private static boolean isAmicableNumber(int n) {
        final int a = sumFactors(findFactors(n));
        final int b = sumFactors(findFactors(a));
        return a != b && n == b;
    }

    private static int sumFactors(SortedSet<Integer> factors) {
        return factors.stream()
                .reduce(0, Integer::sum);
    }

    private static SortedSet<Integer> findFactors(int n) {
        atomicCounter.getAndIncrement();
        final SortedSet<Integer> factors = new TreeSet<>();
        for (int i = 1; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                factors.add(i);
                if (i != 1) {
                    factors.add(n / i);
                }
            }
        }
        return Collections.unmodifiableSortedSet(factors);
    }

}
