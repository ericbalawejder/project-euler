package net.euler.project.fifty;

/**
 * (Sigma n)^2 - Sigma^2 n
 */
public class Problem6SumSquareDifference {

    public static void main(String[] args) {
        System.out.println(computeDifferenceOfSquares(100));
    }

    static int computeDifferenceOfSquares(int n) {
        return computeSquareOfSumTo(n) - computeSumOfSquaresTo(n);
    }

    private static int computeSquareOfSumTo(int n) {
        return (int) Math.pow((n * (n + 1)) / 2.0, 2);
    }

    private static int computeSumOfSquaresTo(int n) {
        return (n * (n + 1) * (2 * n + 1)) / 6;
    }

}
