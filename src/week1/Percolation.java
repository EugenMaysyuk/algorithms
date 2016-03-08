package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation.
 * Given a composite systems comprised of randomly distributed insulating and
 * metallic materials: what fraction of the materials need to be metallic so that
 * the composite system is an electrical conductor?
 * Given a porous landscape with water on the surface (or oil below),
 * under what conditions will the water be able to drain through to the bottom
 * (or the oil to gush through to the surface)?
 * Scientists have defined an abstract process known as percolation to model such situations.
 * <p>
 * Created by Eugene on 10-Feb-16.
 */
public class Percolation {

    private final int n;
    private final int top;
    private final int bottom;
    private boolean[][] sites;
    private WeightedQuickUnionUF qu;
    private WeightedQuickUnionUF qu2;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.sites = new boolean[n][n];
        this.qu = new WeightedQuickUnionUF(n * n + 2);
        this.qu2 = new WeightedQuickUnionUF(n * n + 1); // newly opened sites of this qu are not connected with fake bottom site
        this.top = n * n;
        this.bottom = n * n + 1;
    }

    public void open(int i, int j) {
        if (isValid(i) && isValid(j)) {

            if (!sites[i - 1][j - 1]) {

                sites[i - 1][j - 1] = true;
                int opened = (i - 1) * n + (j - 1);

                if (i == 1) {
                    qu.union(top, opened);
                    qu2.union(top, opened);
                }

                if (i == n) {
                    qu.union(bottom, opened);
                    // we need to prevent connection qu2 with bottom to prevent
                    // connection groups with water and groups without water via fake bottom site
                }

                if (j > 1 && isOpen(i, j - 1)) {
                    qu.union(opened, opened - 1);
                    qu2.union(opened, opened - 1);
                }

                if (j < n && isOpen(i, j + 1)) {
                    qu.union(opened, opened + 1);
                    qu2.union(opened, opened + 1);
                }

                if (i > 1 && isOpen(i - 1, j)) {
                    qu.union(opened, ((i - 1) - 1) * n + (j - 1));
                    qu2.union(opened, ((i - 1) - 1) * n + (j - 1));
                }

                if (i < n && isOpen(i + 1, j)) {
                    qu.union(opened, ((i - 1) + 1) * n + (j - 1));
                    qu2.union(opened, ((i - 1) + 1) * n + (j - 1));
                }

            }

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isValid(int i) {
        return i > 0 && i <= n;
    }

    public boolean isOpen(int i, int j) {
        if (isValid(i) && isValid(j)) {
            return sites[i - 1][j - 1];
        }

        throw new IndexOutOfBoundsException();
    }

    public boolean isFull(int i, int j) {
        if (isValid(i) && isValid(j)) {
            return qu2.connected((i - 1) * n + (j - 1), top);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean percolates() {
        return qu.connected(top, bottom);
    }

}
