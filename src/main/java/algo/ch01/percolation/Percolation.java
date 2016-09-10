package algo.ch01.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * algs4-week-homework-percolation
 * Created by chiyx on 2016/9/9.
 */
public class Percolation {

    private int n;

    // the grid, true for open
    private boolean[][] grid;

    // for percolates judge, has top adn bottom virSite
    private WeightedQuickUnionUF uf1;

    // for full judge, has top virSite
    private WeightedQuickUnionUF uf2;

    private int virTopSiteIdx;

    private int virBotSiteIdx;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }
        this.n = n;
        this.grid = new boolean[n][n];
        int numOfSite = n * n;
        this.uf1 = new WeightedQuickUnionUF(numOfSite + 2);
        this.uf2 = new WeightedQuickUnionUF(numOfSite + 1);
        virTopSiteIdx = numOfSite; // the range of uf1 is [0, n * n + 1]
        virBotSiteIdx = numOfSite + 1;
        // link top row to virTopSite, do for 2 uf
        for (int col = 1; col <= n; col++) {
            uf1.union(toUfIndex(1, col), virTopSiteIdx);
            uf2.union(toUfIndex(1, col), virTopSiteIdx);
        }
        // link bot row to virBotSite, just do for uf1
        for (int col = 1; col <= n; col++) {
            uf1.union(toUfIndex(n, col), virBotSiteIdx);
        }
    }

    public void open(int i, int j) {
        assetsBounds(i, j);
        if (!isOpen(i, j)) {
            // mark open
            grid[i - 1][j - 1] = true;
            int curIdx = toUfIndex(i, j);
            // connect to neighbor open site
            // 1. neighbor of previous row
            unionOpenNeighbor(curIdx, i - 1, j);
            // 2. neighbor of next col
            unionOpenNeighbor(curIdx, i, j + 1);
            // 3. neighbor of next row
            unionOpenNeighbor(curIdx, i + 1, j);
            // 4. neighbor of previous col
            unionOpenNeighbor(curIdx, i, j - 1);
        }
    }

    public boolean isOpen(int i, int j) {
        assetsBounds(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        assetsBounds(i, j);
        // open && connected to virTopSite, use uf2 to judge
        return isOpen(i, j) && uf2.connected(toUfIndex(i, j), virTopSiteIdx);
    }

    public boolean percolates() {
        return uf1.connected(virTopSiteIdx, virBotSiteIdx);
    }

    private void unionOpenNeighbor(int curIdx, int neighborI, int neighborJ) {
        int neighborIdx = toUfIndex(neighborI, neighborJ);
        if (isInBoundsAndOpen(neighborI, neighborJ)) {
            uf1.union(curIdx, neighborIdx);
            uf2.union(curIdx, neighborIdx);
        }
    }

    /**
     * to uf index
     *
     * @param i row of grid
     * @param j col of grid
     * @return index of uf
     */
    private int toUfIndex(int i, int j) {
        return n * (i - 1) + (j - 1);
    }

    private boolean isInBoundsAndOpen(int row, int col) {
        return isInBounds(row) && isInBounds(col) && isOpen(row, col);
    }

    private void assetsBounds(int row, int col) {
        if (!isInBounds(row) || !isInBounds(col)) {
            throw new IndexOutOfBoundsException("row or col must in range [1, n]");
        }
    }

    private boolean isInBounds(int i) {
        return i >= 1 && i <= n;
    }
}
