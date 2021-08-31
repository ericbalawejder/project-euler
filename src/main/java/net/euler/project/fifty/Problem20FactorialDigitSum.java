package net.euler.project.fifty;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Compute 100! with BigInteger and then sum the digits.
 */
public class Problem20FactorialDigitSum {

    public static void main(String[] args) {
        System.out.println(addDigits(factorial(100)));
    }

    static int addDigits(BigInteger number) {
        return Arrays.stream(number.toString().split(""))
                .map(Integer::parseInt)
                .reduce(0, Math::addExact);
    }

    private static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("input must be greater or equal to 0");
        } else if (n == 0) {
            return BigInteger.ONE;
        } else {
            return Stream.iterate(BigInteger.TWO,
                            i -> i.compareTo(BigInteger.valueOf(n)) <= 0,
                            i -> i.add(BigInteger.ONE))
                    .reduce(BigInteger.ONE, BigInteger::multiply);
        }
    }

}
