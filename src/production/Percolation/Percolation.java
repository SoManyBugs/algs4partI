/**
 * This file is created by @Muffin_C on 1/21/15 20:55.
 * This file is part of Project algs4partI.
 */
public class Percolation {

    private WeightedQuickUnionUF disjointSet;
    private WeightedQuickUnionUF backup;
    private int N;
    private boolean[] sitesStatus;
    private int topIndex;
    private int bottomIndex;

    public Percolation(int N) {
        // create N-by-N grid, with all sitesStatus blocked
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        disjointSet = new WeightedQuickUnionUF(N * N + 2);  // [N * N] as topIndex node, [N * N + 1] as bottom node
        backup = new WeightedQuickUnionUF(N * N + 1);
        topIndex = N * N;
        bottomIndex = N * N + 1;

        for (int i = 0; i < N; i++) {
            disjointSet.union(i, topIndex);
            backup.union(i, topIndex);
            disjointSet.union(i + N * (N - 1), bottomIndex);
        }

        sitesStatus = new boolean[N * N];

    }

    public static void main(String[] args) { // test client (optional)

    }

    private boolean isLegalIndex(int i, int j) {
        return !(j < 1 || j > N || i < 1 || i > N);
    }

    private int ijToIndex(int i, int j) {
        return (j - 1) + (i - 1) * N;
    }

    public void open(int i, int j) {
        // open site (row i, column j) if it is not open already
        if (!isLegalIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        int index = ijToIndex(i, j);

        if (!sitesStatus[index]) {
            sitesStatus[index] = true;

            for (int iShift = -1; iShift <= 1; iShift++) {
                for (int jShift = -1; jShift <= 1; jShift++) {
                    if (isLegalIndex(i + iShift, j + jShift) && iShift * jShift == 0) {
                        if (sitesStatus[ijToIndex(i + iShift, j + jShift)]) {
                            disjointSet.union(ijToIndex(i + iShift, j + jShift), ijToIndex(i, j));
                            backup.union(ijToIndex(i + iShift, j + jShift), ijToIndex(i, j));
                        }
                    }
                }
            }
        }
    }

    public boolean isOpen(int i, int j) {
        // is site (row i, column j) open?
        if (!isLegalIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return sitesStatus[ijToIndex(i, j)];
    }

    public boolean isFull(int i, int j) {
        // is site (row i, column j) full?
        if (!isLegalIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        if (sitesStatus[ijToIndex(i, j)]) {
            return backup.connected(ijToIndex(i, j), topIndex) && disjointSet.connected(ijToIndex(i, j), topIndex);
        } else {
            return false;
        }
    }

    public boolean percolates() { // does the system percolate?
        if (N == 1) {
            return sitesStatus[0];
        }

        return disjointSet.connected(topIndex, bottomIndex);
    }
}