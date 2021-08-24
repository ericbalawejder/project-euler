package net.euler.project.fifty;

import java.util.Collections;
import java.util.OptionalLong;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.LongStream;

/**
 * Find all prime factors and put them in a tree set. We don't care about duplicates (powers).
 * Grab the last element in the set.
 */
public class Problem3LargestPrimeFactor {

    public static void main(String[] args) {
        System.out.println(findLargestPrimeFactorOf(600851475143L));
    }

    static long findLargestPrimeFactorOf(long number) {
        return calculatePrimeFactorsOf(number).last();
    }

    private static SortedSet<Long> calculatePrimeFactorsOf(long number) {
        final OptionalLong lowestPrime = LongStream.rangeClosed(2, number)
                .filter(i -> number % i == 0)
                .findFirst();

        final SortedSet<Long> primeFactors = new TreeSet<>();
        if (lowestPrime.isPresent()) {
            primeFactors.add(lowestPrime.getAsLong());
            primeFactors.addAll(calculatePrimeFactorsOf(number / lowestPrime.getAsLong()));
        }
        return Collections.unmodifiableSortedSet(primeFactors);
    }

}
