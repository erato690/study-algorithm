package princeton.wekend.first;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] threshold;
    private int sitesOpen;
    private final double percolationThreshold, mean, confidenceHigh, confidenceLow;

    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("O tamanho da grade deve ser maior que 0.");
        }

        sitesOpen = 0;

        threshold = new double[trials];

        for (int count = 0; count < trials; count++) {
            Percolation percolation = new Percolation(n);
            sitesOpen = 0;

            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);

                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    sitesOpen++;
                }
            }
            threshold[count] = (double) sitesOpen / (n * n);
        }

        percolationThreshold = StdStats.stddev(threshold);
        mean = StdStats.mean(threshold);
        double s = 1.96 * percolationThreshold / Math.sqrt(trials);
        confidenceLow = mean - s;
        confidenceHigh = mean + s;

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return percolationThreshold;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return confidenceHigh;
    }


    public static void main(String[] args) {

        PercolationStats percolationStats = new PercolationStats(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1])
        );
        System.out.println("Mean                    = " + percolationStats.mean());
        System.out.println("StdDev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}
