package algo.ch01.percolation;

import edu.princeton.cs.algs4.StdStats;

/**
 * algs4-week-homework-percolation
 * Created by chiyx on 2016/9/9.
 */
public class PercolationStats {

    private double[] thresholds;

    private int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        this.thresholds = new double[trials];
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        double u = mean();
        double b = stddev();
        return u - (1.96 * b) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        double u = mean();
        double b = stddev();
        return u + (1.96 * b) / Math.sqrt(trials);
    }

    public static void main(String[] args) {

    }
}
