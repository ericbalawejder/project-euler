package net.euler.project.fifty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Storing the factors in a map is not necessary but was interesting for visualization.
 * 100 - 999 are all possible 3 digit values.
 */
public class Problem4LargestPalindromeProduct {

    public static void main(String[] args) {
        System.out.println(findLargestPalindromeProduct(100, 999));
    }

    static long findLargestPalindromeProduct(int start, int end) {
        return getPalindromeProductsWithFactors(start, end).lastKey();
    }

    private static SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int start, int end) {
        final SortedMap<Long, List<List<Integer>>> palindromes = new TreeMap<>();

        if (start > end) {
            throw new IllegalArgumentException(
                    String.format("invalid input: min is %d and max is %d", start, end));
        } else {
            for (int i = start; i <= end; i++) {
                for (int j = i; j <= end; j++) {
                    Long product = (long) i * j;
                    if (isPalindrome(product)) {
                        List<List<Integer>> factors = new ArrayList<>();
                        if (palindromes.containsKey(product)) {
                            factors.addAll(palindromes.get(product));
                        }
                        factors.add(Arrays.asList(i, j));
                        palindromes.put(product, factors);
                    }
                }
            }
        }
        return Collections.unmodifiableSortedMap(palindromes);
    }

    private static boolean isPalindrome(long number) {
        return number == reverseNumber(number);
    }

    private static long reverseNumber(long number) {
        long reversedNumber = 0L;

        while (number != 0) {
            reversedNumber = reversedNumber * 10 + number % 10;
            number = number / 10;
        }
        return reversedNumber;
    }

}
