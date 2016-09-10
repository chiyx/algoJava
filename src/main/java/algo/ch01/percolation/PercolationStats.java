package algo.ch01.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * algs4-week-homework-percolation
 * Created by chiyx on 2016/9/9.
 */
public class PercolationStats {

    private double[] thresholds;

    private int trials;

    private int n;

    private double mean;

    private double stddev;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n or trials <= 0 ");
        }
        this.trials = trials;
        this.n = n;
        this.thresholds = new double[trials];
        percolationRun();
        this.mean = StdStats.mean(thresholds);
        this.stddev = StdStats.stddev(thresholds);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        double u = mean;
        double b = stddev;
        return u - (1.96 * b) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        double u = mean;
        double b = stddev;
        return u + (1.96 * b) / Math.sqrt(trials);
    }

    private void percolationRun() {
        int numOfSite = n * n;
        // run trial times
        for (int i = 0; i < trials; i++) {
            int numOfOpenSite = 0;
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int randI = StdRandom.uniform(n) + 1;
                int randJ = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(randI, randJ)) {
                    percolation.open(randI, randJ);
                    numOfOpenSite++;
                }
            }
            thresholds[i] = numOfOpenSite / (double) numOfSite;
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println("You must pass two arguments");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trails);
        double mean = stats.mean();
        double stddev = stats.stddev();
        StdOut.println("mean                    = " + mean);
        StdOut.println("stddev                  = " + stddev);
        StdOut.println("95% confidence interval = "
                +
                stats.confidenceLo()
                + ", "
                +
                stats.confidenceHi());
    }

}
