package net.euler.project.fifty;

import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Every third term is even.
 * Modified fibonacciSupplier to supply every third term.
 * Used BigInteger with fib numbers.
 */
public class Problem2EvenFibonacciNumbers {

    public static void main(String[] args) {
        System.out.println(fibonacciSum(new BigInteger("4000000")));
    }

    static BigInteger fibonacciSum(BigInteger limit) {
        return Stream.generate(new evenFibonacciSupplier())
                .takeWhile(f -> f.compareTo(limit) < 0)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private static class evenFibonacciSupplier implements Supplier<BigInteger> {
        private BigInteger b = BigInteger.ONE;
        private BigInteger c = BigInteger.ZERO;

        @Override
        public BigInteger get() {
            BigInteger a = b.add(c);
            b = a.add(c);
            c = a.add(b);
            return c;
        }
    }

    private static class FibonacciSupplier implements Supplier<BigInteger> {
        private BigInteger a = BigInteger.ZERO;
        private BigInteger b = BigInteger.ONE;

        @Override
        public BigInteger get() {
            BigInteger fib = a.add(b);
            a = b;
            b = fib;
            return fib;
        }
    }

}
