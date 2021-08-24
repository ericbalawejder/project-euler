package net.euler.project.fifty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 *  Uses sieve to generate primes.
 */
public class Problem10SummationOfPrimes {

    public static void main(String[] args) {
        System.out.println(summationOfPrimes(2_000_000L));
    }

    static long summationOfPrimes(long limit) {
        return sieve(limit)
                .stream()
                .reduce(0L, Long::sum);
    }

    private static List<Long> sieve(long limit) {
        final Set<Long> notPrime = new HashSet<>();

        LongStream.rangeClosed(2, (long) Math.sqrt(limit))
                .flatMap(x -> LongStream.iterate(x * x, n -> n <= limit, n -> x + n))
                .forEach(notPrime::add);

        return LongStream.rangeClosed(2, limit)
                .filter(x -> !notPrime.contains(x))
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

}
