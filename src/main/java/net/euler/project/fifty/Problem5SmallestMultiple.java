package net.euler.project.fifty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.LongStream;

public class Problem5SmallestMultiple {

    /**
     * Divisibility properties
     * k = 20
     * (List of primes < 20) + (largest prime powers < 20) - (prime powers < largest prime power)
     * 5, 7, 9, 11, 13, 16, 17, 19
     * <p>
     * TODO build a function for any k
     * Use set?
     * set of primes < k: A = (2, 3, 5, 7, 11, 13, 17, 19)
     * set with prime powers < k: B = (2, 3, 4, 5, 7, 8, 9, 11, 13, 16, 17, 19)
     * complete set of prime divisors < k: C = (2, 2, 2, 2, 3, 3, 5, 7, 11, 13, 17, 19)
     * A U B - ???
     */
    public static void main(String[] args) {
        System.out.println(5 * 7 * 9 * 11 * 13 * 16 * 17 * 19);
    }

    // Finds all occurrences of primes.
    private static List<Long> calculatePrimeFactorsOf(long number) {
        final OptionalLong lowestPrime = LongStream.rangeClosed(2, number)
                .filter(i -> number % i == 0)
                .findFirst();

        final List<Long> primeFactors = new ArrayList<>();
        if (lowestPrime.isPresent()) {
            primeFactors.add(lowestPrime.getAsLong());
            primeFactors.addAll(calculatePrimeFactorsOf(number / lowestPrime.getAsLong()));
        }
        return Collections.unmodifiableList(primeFactors);
    }

}
