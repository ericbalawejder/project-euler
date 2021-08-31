package net.euler.project.fifty;

import java.util.stream.LongStream;

public class Problem14LongestCollatzSequence {

    public static void main(String[] args) {
        System.out.println(longestCollatzSequence(1, 1_000_000));
    }

    static int longestCollatzSequence(int start, int limit) {
        long max = -1;
        int index = start;
        for (int i = start; i < limit; i++) {
            long stepCount = computeStepCount(i);
            if (stepCount > max) {
                max = stepCount;
                index = i;
            }
        }
        return index;
    }

    private static long computeStepCount(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Only natural numbers are allowed");
        }
        return LongStream.iterate(number, n -> n > 1, n -> n % 2 == 0 ? n / 2 : 3 * n + 1)
                .count();
    }

}
