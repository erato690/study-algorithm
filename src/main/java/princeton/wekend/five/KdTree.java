package princeton.wekend.five;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private Node root;

    public KdTree() {

    }

    private static class Node {
        private Point2D point;
        private Node left, right;
        private int count;

        public Node(Point2D point, int count) {
            this.count = count;
            this.point = point;
        }

    }

    public void insert(Point2D point) {
        root = insert(root, point, 0);
    }

    private Node insert(Node node, Point2D point, int depth) {

        if (node == null) {
            return new Node(point, 1); // Supondo que '1' é a contagem inicial
        }

        if (node.point.equals(point)) {
            return node; // Ponto duplicado, não insere
        }

        int dim = depth % 2; // 2 para uma KD-Tree 2D

        if ((dim == 0 && point.x() < node.point.x()) || (dim == 1 && point.y() < node.point.y())) {
            node.left = insert(node.left, point, depth + 1);
        } else {
            node.right = insert(node.right, point, depth + 1);
        }

        node.count = 1 + size(node.left) + size(node.right);


        return node;
    }


    public boolean contains(Point2D key) {

        if (key == null)
            throw new IllegalArgumentException("Point2D is null");

        return get(root, key, 0) != null;
    }

    private Node get(Node node, Point2D point2D, int depth) {
        if (node == null) {
            return null;
        }

        int dim = depth % 2; // Alternância entre os eixos x e y

        if (dim == 0) {
            if (point2D.x() < node.point.x()) {
                return get(node.left, point2D, depth + 1);
            } else if (point2D.x() > node.point.x()) {
                return get(node.right, point2D, depth + 1);
            } else {
                // Coordenada x é igual, verifique y em ambos os lados
                Node found = get(node.left, point2D, depth + 1);
                if (found == null) {
                    found = get(node.right, point2D, depth + 1);
                }
                return (found != null) ? found : (point2D.y() == node.point.y() ? node : null);
            }
        } else {
            if (point2D.y() < node.point.y()) {
                return get(node.left, point2D, depth + 1);
            } else if (point2D.y() > node.point.y()) {
                return get(node.right, point2D, depth + 1);
            } else {
                // Coordenada y é igual, verifique x em ambos os lados
                Node found = get(node.left, point2D, depth + 1);
                if (found == null) {
                    found = get(node.right, point2D, depth + 1);
                }
                return (found != null) ? found : (point2D.x() == node.point.x() ? node : null);
            }
        }
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node root) {
        if (root == null)
            return;
        draw(root.left);
        root.point.draw();
        draw(root.right);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.count;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }


    public Iterable<Point2D> range(RectHV rect) {

        if (rect == null)
            throw new IllegalArgumentException("RectHV is null");

        List<Point2D> listPoints = new ArrayList<>();
        searchRange(root, new Point2D(rect.xmin(), rect.ymin()), new Point2D(rect.xmax(), rect.ymax()), 0, listPoints);


        return listPoints;
    }

    private void searchRange(Node root, Point2D pointLeft, Point2D pointRight, int depth, List<Point2D> listPoints) {
        if (root == null) {
            return;
        }

        var actualPoint = root.point;
        var x = actualPoint.x();
        var y = actualPoint.y();


        if (x >= pointLeft.x() && x <= pointRight.x() && y >= pointLeft.y() && y <= pointRight.y()) {
            if (!listPoints.contains(actualPoint))
                listPoints.add(actualPoint);
        }

        int dim = depth % 2;

        if (dim == 0) {
            if (x >= pointLeft.x()) {
                searchRange(root.left, pointLeft, pointRight, depth + 1, listPoints);
            }
            if (x <= pointRight.x()) {
                searchRange(root.right, pointLeft, pointRight, depth + 1, listPoints);
            }
        } else {
            if (y >= pointLeft.y()) {
                searchRange(root.left, pointLeft, pointRight, depth + 1, listPoints);
            }
            if (y <= pointRight.y()) {
                searchRange(root.right, pointLeft, pointRight, depth + 1, listPoints);
            }
        }
    }

    public Point2D nearest(Point2D point2D) {

        if (point2D == null)
            throw new IllegalArgumentException("Point2D is null");

        return this.nearest(point2D, root, 0, null);
    }

    private Point2D nearest(Point2D consultPoint, Node node, int depth, Point2D betterNeighbour) {

        if (node == null) {
            return betterNeighbour;
        }

        int dim = depth % 2;

        var actualDistance = consultPoint.distanceSquaredTo(node.point);

        Node rigth = null;


        if ((dim == 0 && consultPoint.x() < node.point.x()) || (dim == 1 && consultPoint.y() < node.point.y())) {
            betterNeighbour = nearest(consultPoint, node.left, depth + 1, betterNeighbour);
            rigth = node.right;
        } else {
            betterNeighbour = nearest(consultPoint, node.right, depth + 1, betterNeighbour);
            rigth = node.left;
        }


        if (betterNeighbour == null || actualDistance < consultPoint.distanceSquaredTo(betterNeighbour)) {
            betterNeighbour = node.point;
        }

        double distDim = dim == 0 ? Math.abs(consultPoint.x() - node.point.x()) : Math.abs(consultPoint.y() - node.point.y());

        if (distDim < consultPoint.distanceTo(betterNeighbour)) {
            Point2D candidate = nearest(consultPoint, rigth, depth + 1, betterNeighbour);
            if (candidate != null && consultPoint.distanceSquaredTo(candidate) < consultPoint.distanceSquaredTo(betterNeighbour)) {
                betterNeighbour = candidate;
            }
        }

        return betterNeighbour;

    }

    public static void main(String[] args) {


        In in = new In("rangeTest.txt");
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }


        StdOut.print(kdtree.contains(new Point2D(0.375, 0.75)));
        StdOut.println(kdtree.size());

    }


}
