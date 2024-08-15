package princeton.wekend.first;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class PercolationVisualizer {
    // draw N-by-N percolation system
    public static void draw(Percolation perc, int N) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);

        // draw N-by-N grid
        int opened = 0;
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (perc.isFull(row, col))
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                else if (perc.isOpen(row, col))
                    StdDraw.setPenColor(StdDraw.WHITE);
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, N - row + 0.5, 0.45);
                if (perc.isOpen(row, col)) opened++;
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.25 * N, -N * .025, opened + " open sites");
        if (perc.percolates()) StdDraw.text(.75 * N, -N * .025, "percolates");
        else StdDraw.text(.75 * N, -N * .025, "does not percolate");

    }

    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // N-by-N percolation system

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(N);
        draw(perc, N);
        while (!in.isEmpty()) {
            StdDraw.show(0);          // turn on animation mode
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
            draw(perc, N);
            StdDraw.show(10);
            System.out.println("site open " + i + " " + j + " is full:" + perc.isFull(i, j) + " is percole:" + perc.percolates() + " sum sites opens:" + perc.numberOfOpenSites());// pause for 100 miliseconds
        }
    }
}
