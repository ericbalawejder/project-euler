package net.euler.project.fifty;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Problem25ThousandDigitFibonacciNumber {

    public static void main(String[] args) {
        System.out.println(fibonacciLength(1000));
        System.out.println(binetsFormula(1000));
    }

    static double binetsFormula(int numberOfDigits) {
        return round((numberOfDigits - 1 + Math.log10(Math.sqrt(5))) / Math.log10((1 + Math.sqrt(5)) / 2), 0);
    }

    static long fibonacciLength(int numberOfDigits) {
        return Stream.generate(new Problem25ThousandDigitFibonacciNumber.FibonacciSupplier())
                .takeWhile(fib -> fib.toString().length() < numberOfDigits)
                .count() + 2;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        final BigDecimal bigDecimal = BigDecimal.valueOf(value);
        BigDecimal roundedValue = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return roundedValue.doubleValue();
    }

    // Starts at F2 = 1
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
