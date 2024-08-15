package princeton.wekend.five;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> point2DS;

    public PointSET() {
        this.point2DS = new TreeSet<>();
    }

    public int size() {
        return point2DS.size();
    }


    public boolean isEmpty() {
        return point2DS.isEmpty();
    }

    public void insert(Point2D point2D) {

        if (point2D == null)
            throw new IllegalArgumentException("Point2D is null");

        point2DS.add(point2D);
    }

    public boolean contains(Point2D point2D) {

        if (point2D == null)
            throw new IllegalArgumentException("Point2D is null");

        return point2DS.contains(point2D);
    }

    public void draw() {
        for (Point2D point2D : point2DS) {
            point2D.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rectHV) {

        if (rectHV == null)
            throw new IllegalArgumentException("RectHV is null");

        TreeSet<Point2D> points = new TreeSet<>();

        for (Point2D point2D : point2DS) {
            if (rectHV.contains(point2D))
                points.add(point2D);
        }

        return points;
    }

    public Point2D nearest(Point2D point2D) {

        if (point2D == null)
            throw new IllegalArgumentException("Point2D is null");

        Point2D nearest = null;


        for (Point2D point : point2DS) {
            if (nearest == null || point.distanceSquaredTo(point2D) < nearest.distanceSquaredTo(point2D))
                nearest = point;
        }

        return nearest;
    }
}
