package net.euler.project.fifty;

import java.math.BigInteger;
import java.util.Arrays;

public class Problem16PowerDigitSum {

    public static void main(String[] args) {
        System.out.println(computePowerDigitSum(2, 1000));
    }

    static int computePowerDigitSum(int base, int exponent) {
        final String value = BigInteger.valueOf(base)
                .pow(exponent)
                .toString();

        return Arrays.stream(value.split(""))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

}
