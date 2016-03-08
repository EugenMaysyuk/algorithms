package week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * PercolationStats.
 * This class was created to perform a series of computational experiments on Percolation.
 * <p>
 * Created by Eugene on 10-Feb-16.
 */
public class PercolationStats {

    private final int t;
    private double[] x;

    public PercolationStats(int n, int t) {

        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }

        this.t = t;
        this.x = new double[t];

        for (int i = 0; i < t; i++) {

            Percolation p = new Percolation(n);
            int count = 0;

            while (!p.percolates()) {
                int randI = StdRandom.uniform(1, n + 1);
                int randJ = StdRandom.uniform(1, n + 1);
                if (!p.isOpen(randI, randJ)) {
                    p.open(randI, randJ);
                    count++;
                }
            }

            x[i] = count / Math.pow(n, 2);

        }
    }

    public double mean() {
        return StdStats.mean(x);
    }

    public double stddev() {
        return StdStats.stddev(x);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(t));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(t));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
