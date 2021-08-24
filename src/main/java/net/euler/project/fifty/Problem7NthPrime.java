package net.euler.project.fifty;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * Used BigInteger for prime number check because knowing the distribution of primes
 * is a separate problem, especially for large P(x).
 */
public class Problem7NthPrime {

    public static void main(String[] args) {
        System.out.println(nthPrime(10001));
    }

    static BigInteger nthPrime(int nth) {
        return Stream.iterate(BigInteger.TWO, i -> i.add(BigInteger.ONE))
                .filter(Problem7NthPrime::isPrime)
                .limit(nth)
                .max(BigInteger::compareTo)
                .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.ONE) <= 0) {
            throw new IllegalArgumentException("Number must be larger than 1.");
        }
        if (number.equals(BigInteger.TWO) || number.equals(new BigInteger("3"))) {
            return true;
        }
        if (number.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }
        return Stream.iterate(new BigInteger("3"),
                        n -> n.compareTo(number.sqrt()) <= 0,
                        n -> n.add(BigInteger.TWO))
                .noneMatch(n -> number.mod(n).equals(BigInteger.ZERO));
    }

}
