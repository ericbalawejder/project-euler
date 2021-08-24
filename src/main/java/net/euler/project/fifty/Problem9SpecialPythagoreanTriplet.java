package net.euler.project.fifty;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Used builder pattern. Placed triplets in collection for multiple values.
 * Used 500 as upper bound for factors. a + b = c has area zero.
 */
public class Problem9SpecialPythagoreanTriplet {

    private final int a;
    private final int b;
    private final int c;

    public static void main(String[] args) {
        final SortedSet<Long> triplets
                = Problem9SpecialPythagoreanTriplet
                .makeTripletsList()
                .withFactorsLessThanOrEqualTo(500)
                .thatSumTo(1000)
                .build()
                .stream()
                .map(Problem9SpecialPythagoreanTriplet::calculateProduct)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println(triplets);
    }

    public Problem9SpecialPythagoreanTriplet(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int calculateSum() {
        return a + b + c;
    }

    public long calculateProduct() {
        return (long) a * b * c;
    }

    public boolean isPythagorean() {
        return Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2);
    }

    public static PythagoreanTripletBuilder makeTripletsList() {
        return new PythagoreanTripletBuilder();
    }

    @Override
    public String toString() {
        return a + " " + b + " " + c;
    }

    public static class PythagoreanTripletBuilder {
        private int lower = 1;
        private int upper;
        private Integer sum;

        public PythagoreanTripletBuilder withFactorsGreaterThanOrEqualTo(int lower) {
            this.lower = lower;
            return this;
        }

        public PythagoreanTripletBuilder withFactorsLessThanOrEqualTo(int upper) {
            this.upper = upper;
            return this;
        }

        public PythagoreanTripletBuilder thatSumTo(int sum) {
            this.sum = sum;
            return this;
        }

        public List<Problem9SpecialPythagoreanTriplet> build() {
            final List<Problem9SpecialPythagoreanTriplet> results = new ArrayList<>();

            for (int n = 1; n <= Math.sqrt(upper); n++) {
                for (int m = n + 1; m <= Math.sqrt(upper); m++) {
                    if (gcd(m, n) == 1 && !(m % 2 != 0 && n % 2 != 0)) {

                        for (int k = 1; k <= upper / 5; k++) {
                            int a = k * ((int) (Math.pow(m, 2) - Math.pow(n, 2)));
                            int b = k * 2 * m * n;
                            int c = k * ((int) (Math.pow(m, 2) + Math.pow(n, 2)));

                            Problem9SpecialPythagoreanTriplet triplet =
                                    new Problem9SpecialPythagoreanTriplet(a, b, c);
                            if (a >= lower && b >= lower && c <= upper
                                    && (sum == null || triplet.calculateSum() == sum)) {
                                results.add(triplet);
                            }
                        }
                    }
                }
            }
            return List.copyOf(results);
        }

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }

}
