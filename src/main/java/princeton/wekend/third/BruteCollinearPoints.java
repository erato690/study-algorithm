package princeton.wekend.third;//package third;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        checkNullEntries(points);
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        checkDuplicatedEntries(pointsCopy);
        if (pointsCopy.length > 3) {
            Point[] temp = new Point[4];
            for (int i = 0; i < pointsCopy.length - 3; i++) {
                temp[0] = pointsCopy[i];
                for (int j = i + 1; j < pointsCopy.length - 2; j++) {
                    temp[1] = pointsCopy[j];
                    for (int p = j + 1; p < pointsCopy.length - 1; p++) {
                        temp[2] = pointsCopy[p];
                        for (int k = p + 1; k < pointsCopy.length; k++) {
                            temp[3] = pointsCopy[k];
                            if (collinear(temp[0], temp[1], temp[2], temp[3])) {
                                LineSegment segment = getSeg(temp);
                                segmentsList.add(segment);
                            }
                        }
                    }
                }
            }
        }

        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);

    }

    private LineSegment getSeg(Point[] temp) {
        Arrays.sort(temp);
        return new LineSegment(temp[0], temp[3]);
    }


    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    private boolean collinear(Point p, Point q, Point r, Point s) {
        double slop1 = p.slopeTo(q);
        double slop2 = p.slopeTo(r);
        double slop3 = p.slopeTo(s);
        return (Double.compare(slop1, slop2) == 0) && (Double.compare(slop1, slop3) == 0);
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
        // read the n points from a file
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
