package net.euler.project.fifty;

/**
 * Modified sigma n
 */
public class Problem1SumOfMultiples {

    public static void main(String[] args) {
        final int sum = sumOfMultiples(3, 1000)
                + sumOfMultiples(5, 1000)
                - sumOfMultiples(15, 1000);

        System.out.println(sum);
    }

    static int sumOfMultiples(int multiple, int limit) {
        final int n = (limit - 1) / multiple;
        return (multiple * (n * (n + 1))) / 2;
    }

}
