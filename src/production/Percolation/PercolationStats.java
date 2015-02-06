/**
 * This file is created by @Muffin_C on 1/22/15 16:06.
 * This file is part of Project algs4partI.
 */
public class PercolationStats {
    private int T;
    private double[] openSites;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.T = T;
        openSites = new double[T];

        for (int foo = T; foo > 0; foo--) {
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;

                if (!perc.isOpen(i, j)) {
                    perc.open(i, j);
                    openSites[foo - 1]++;
                }
            }
            openSites[foo - 1] /= Math.pow(N, 2);
        }
    } // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(openSites);
    } // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(openSites);
    } // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    } // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    } // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int N;
        int T;
        if (args.length == 2) {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
            PercolationStats perc = new PercolationStats(N, T);
            System.out.println("mean                    = " + perc.mean());
            System.out.println("stddev                  = " + perc.stddev());
            System.out.println("95% confidence interval = "
                    + perc.confidenceLo() + ", "
                    + perc.confidenceHi());
        }


    } // test client (described below) // test client (described below)
}
