package net.euler.project.fifty;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Place numbers in a text file and read into a list of type BigInteger.
 * (Most Advent of Code problems work this way). Sum / 10^(length-10) to
 * truncate all but ten digits.
 */
public class Problem13LargeSum {

    public static void main(String[] args) {
        final String path = "src/main/java/net/euler/project/fifty/data/problem13-numbers.txt";
        final List<BigInteger> numbers = readFile(path);
        System.out.println(findFirstNDigits(sum(numbers), 10));
    }

    static BigInteger findFirstNDigits(BigInteger value, int numberOfDigits) {
        final String number = value.toString();
        if (number.length() == numberOfDigits) {
            return value;
        } else if (number.length() < numberOfDigits) {
            throw new IllegalArgumentException("value is less than " + numberOfDigits + " digits");
        } else {
            final int exponent = number.length() - numberOfDigits;
            return value.divide(BigInteger.TEN.pow(exponent));
        }
    }

    private static BigInteger sum(List<BigInteger> numbers) {
        return numbers.stream().reduce(BigInteger.ZERO, BigInteger::add);
    }

    private static List<BigInteger> readFile(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.map(BigInteger::new)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
