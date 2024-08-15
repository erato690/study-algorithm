package princeton.wekend.third;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final LineSegment[] segments;


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        this.checkNullEntries(points);

        // copy input parameter to avoid direct modify
        Point[] localPoints = points.clone();

        // sort local points to avoid mutate input
        Arrays.sort(localPoints);
        checkDuplicatedEntries(localPoints);
        ArrayList<LineSegment> res = new ArrayList<>();

        if (localPoints.length > 3) {
            Point[] temp = localPoints.clone();
            // loop through points in backup array, and sort the temp points array
            for (Point p : localPoints) {
                Arrays.sort(temp, p.slopeOrder());
                findSegments(temp, p, res);
            }
        }

        segments = res.toArray(new LineSegment[res.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }

    // Helper Methods
    private void findSegments(Point[] points, Point p, ArrayList<LineSegment> res) {
        // start from position 1, since position 0 will be the point p itself
        int start = 1;
        double slop = p.slopeTo(points[1]);

        for (int i = 2; i < points.length; i++) {
            double tempSlop = p.slopeTo(points[i]);
            if (!collinearSlop(tempSlop, slop)) {

                if (i - start >= 3) {
                    Point[] ls = genSegment(points, p, start, i);

                    if (ls[0].compareTo(p) == 0) {
                        res.add(new LineSegment(ls[0], ls[1]));
                    }
                }
                // update
                start = i;
                slop = tempSlop;
            }
        }
        // situation when the last several points in the array are collinear
        if (points.length - start >= 3) {
            Point[] lastPoints = genSegment(points, p, start, points.length);
            if (lastPoints[0].compareTo(p) == 0) {
                res.add(new LineSegment(lastPoints[0], lastPoints[1]));
            }
        }
    }

    private boolean collinearSlop(double tempSlop, double slop) {
        if (Double.compare(slop, tempSlop) == 0)
            return true;
        return false;
    }

    private Point[] genSegment(Point[] points, Point p, int start, int end) {
        ArrayList<Point> temp = new ArrayList<>();
        temp.add(p);
        for (int i = start; i < end; i++) {
            temp.add(points[i]);
        }
        temp.sort(null);
        return new Point[]{temp.get(0), temp.get(temp.size() - 1)};
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicated entries in given points");
            }
        }
    }

    private void checkNullEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("One of the point in points array is null");
            }
        }
    }



    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
