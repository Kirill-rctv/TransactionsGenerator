import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Transaction {
    public static List<List<Double>> getTransaction(int n, int variance, double mean , int minTransactions, int maxTransactions) {

//        Create sums of transaction per day

        double standardDeviation = calc_deviation(variance);

        Random random = new Random();

        List<Double> sumsOfTransactionsPerDay = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            sumsOfTransactionsPerDay.add(random.nextGaussian(mean, standardDeviation));
        }

        print_stats(sumsOfTransactionsPerDay);

//        Create transactions

        List<List<Double>> transactions = new ArrayList<>();

        int minBound = -100000;
        int maxBound = 100000;

        for (int i = 0; i < n; ++i) {
            List<Double> l = new ArrayList<>();
            int lSize = minTransactions + random.nextInt(maxTransactions - minTransactions);
            for (int j = 0; j < lSize - 1; ++j) {
                l.add(random.nextDouble(minBound, maxBound));
            }
            l.add(sumsOfTransactionsPerDay.get(i) - calc_sum(l));
            transactions.add(l);
        }

        return transactions;
    }


    private static double calc_sum(List<Double> l) {
        double sum = 0;
        for (int i = 0; i < l.size(); i++) {
            sum = sum + l.get(i);
        }
        return sum;
    }

    private static double calc_mean(List<Double> l) {
        double sum = 0;
        for (int i = 0; i < l.size(); i++) {
            sum = sum + l.get(i);
        }
        return sum / l.size();
    }

    private static double calc_variance(List<Double> l, double mean) {
        double sum = 0;
        for (int i = 0; i < l.size(); i++) {
            sum = sum + Math.pow((l.get(i) - mean), 2);
        }
        return sum / l.size();
    }

    private static double calc_deviation(double variance) {
        return Math.sqrt(variance);
    }

    private static void print_stats(List<Double> l) {
        double mean = calc_mean(l);
        double variance = calc_variance(l, mean);
        double deviation = calc_deviation(variance);

        System.out.printf("Number of elements  : %d\n", l.size());
        System.out.printf("Mean of elements    : %.2f\n", mean);
        System.out.printf("variance of elements: %.2f\n", variance);
        System.out.printf("Standard deviation  : %.2f\n", deviation);
        System.out.print("\n");
    }
}