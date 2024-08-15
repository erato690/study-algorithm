package princeton.wekend.first;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private final int gridSize;
    private final int gridlength;
    private int numOpenSites, topIdx, bottomIdx;
    private final boolean[] siteOpens;
    private final WeightedQuickUnionUF uf;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("O tamanho da grade deve ser maior que 0.");
        }
        this.gridlength = n;
        this.gridSize = n * n + 2;
        topIdx = n * n;
        bottomIdx = n * n + 1;

        uf = new WeightedQuickUnionUF(gridSize);

        this.siteOpens = new boolean[n * n];

        numOpenSites = 0;

    }


    private int index(int row, int col) {

        return (row - 1) * gridlength + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        isValid(row, col);

        int index = index(row, col);

        if (siteOpens[index]) {
            return;
        }

        this.siteOpens[index] = true;
        numOpenSites++;

        if (row == 1) {
            uf.union(col - 1, topIdx);

        }
        if (row == gridlength) uf.union(index, bottomIdx);


        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(index(row, col), index(row, col - 1));
        }
        if (col < gridlength && isOpen(row, col + 1)) {
            uf.union(index(row, col), index(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(index(row, col), index(row - 1, col));
        }
        if (row < gridlength && isOpen(row + 1, col)) {
            uf.union(index(row, col), index(row + 1, col));
        }

    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        isValid(row, col);

        int index = index(row, col);
        return siteOpens[index];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        return isOpen(row, col) && uf.find(index(row, col)) == uf.find(topIdx);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.uf.find(topIdx) == this.uf.find(bottomIdx);
    }


    private void isValid(int row, int col) {
        if (row >= 1 && row <= gridlength && col >= 1 && col <= gridlength)
            return;
        throw new IllegalArgumentException("Coordinates shall be in 1 .. " + gridlength + " range");
    }


    // test client (optional)

    public static void main(String[] args) {


        In in = new In(args[0]);      // input file
        int n = in.readInt();         // N-by-N percolation system

        // repeatedly read in sites to open and draw resulting system
        Percolation percolation = new Percolation(n);

        int gridSize = 2;
        while (!in.isEmpty()) {
            // turn on animation mode
            int i = in.readInt();
            int j = in.readInt();
            percolation.open(i, j);
            System.out.println("site open " + i + " " + j + " is full:" + percolation.isFull(i, j) + " is percole:" + percolation.percolates() + " sum sites opens:" + percolation.numberOfOpenSites());
        }


//        while (!percolation.percolates()) {
//            int row = StdRandom.uniformInt(1, gridSize + 1);
//            int col = StdRandom.uniformInt(1, gridSize + 1);
//
//            percolation.open(row, col);
//
//        }

        // Verificar se os sítios estão cheios
        System.out.println("IsFull(1, 1): " + percolation.percolates());  // Deve ser true


    }
}
