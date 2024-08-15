package princeton.wekend.five;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KdTree_ {

    private Node root;


    private static final int dimension = 2;

    private static class Node {
        private final Point2D key;

        private Node left, right;


        public Node(Point2D key) {
            this.key = key;
        }

    }

    public KdTree_(List<Point2D> points) {
        root = buildTree(points, 0);
    }

    public KdTree_() {

    }

    private Node buildTree(List<Point2D> points, int depth) {
        if (points.isEmpty()) {
            return null;
        }

        int dim = depth % dimension;

        // Ordena os pontos com base na dimensão atual
        if (dim == 0) {
            points.sort(Comparator.comparingDouble(Point2D::getX));
        } else {
            points.sort(Comparator.comparingDouble(Point2D::getY));
        }

        // Encontra a mediana
        int medianIndex = points.size() / 2;
        Point2D medianPoint = points.get(medianIndex);

        // Cria um novo nó com o ponto mediano
        Node node = new Node(medianPoint);

        // Constrói subárvores esquerda e direita
        List<Point2D> leftPoints = points.subList(0, medianIndex);
        List<Point2D> rightPoints = points.subList(medianIndex + 1, points.size());

        node.left = buildTree(leftPoints, depth + 1);
        node.right = buildTree(rightPoints, depth + 1);

        return node;
    }

    public void insert(Point2D key) {

        root = insert(root, key, 0);
    }

    private Node insert(Node node, Point2D point, int depth) {

        if (node == null) {
            return new Node(point); // Supondo que '1' é a contagem inicial
        }


        int dim = depth % 2; // 2 para uma KD-Tree 2D


        if ((dim == 0 && point.getX() < node.key.getX()) || (dim == 1 && point.getY() < node.key.getY())) {
            node.left = insert(node.left, point, depth + 1);
        } else {
            node.right = insert(node.right, point, depth + 1);
        }


        return node;
    }

    public Point2D searchNeighbour(Point2D point) {
        return searchNeighbour(root, point, 0, null);
    }

    private Point2D searchNeighbour(Node node, Point2D consultPoint, int depth, Point2D betterNeighbour) {
        if (node == null) {
            return betterNeighbour;
        }

        int dim = depth % 2; // 2 para uma KD-Tree 2D

        var actualDistance = Point2D.distance(node.key, consultPoint);

        Node outroLado = null;

        // Decide qual lado explorar primeiro e qual lado explorar depois, se necessário
        if ((dim == 0 && consultPoint.getX() < node.key.getX()) || (dim == 1 && consultPoint.getY() < node.key.getY())) {
            betterNeighbour = searchNeighbour(node.left, consultPoint, depth + 1, betterNeighbour);
            outroLado = node.right;
        } else {
            betterNeighbour = searchNeighbour(node.right, consultPoint, depth + 1, betterNeighbour);
            outroLado = node.left;
        }

        // Atualiza o melhor vizinho, se necessário
        if (betterNeighbour == null || actualDistance < Point2D.distance(consultPoint, betterNeighbour)) {
            betterNeighbour = node.key;
        }

        // Verifica se é necessário explorar o outro lado da árvore
        double distDim = dim == 0 ? Math.abs(consultPoint.getX() - node.key.getX()) : Math.abs(consultPoint.getY() - node.key.getY());

        if (distDim < Point2D.distance(consultPoint, betterNeighbour)) {
            Point2D candidato = searchNeighbour(outroLado, consultPoint, depth + 1, betterNeighbour);
            if (candidato != null && Point2D.distance(consultPoint, candidato) < Point2D.distance(consultPoint, betterNeighbour)) {
                betterNeighbour = candidato;
            }
        }

        return betterNeighbour;
    }


    public List<Point2D> searchRangeRadius(Point2D point, double radius) {
        List<Point2D> listPoints = new ArrayList<>();
        searchRangeRadius(root, point, 0, radius, listPoints);
        return listPoints;
    }

    private void searchRangeRadius(Node root, Point2D point, int depth, double radius, List<Point2D> listPoints) {
        if (root == null) {
            return;
        }

        int dim = depth % 2; // 2 para uma KD-Tree 2D

        var actualPoint = root.key;

        if (Point2D.distance(actualPoint, point) <= radius) {
            listPoints.add(actualPoint);
        }

        if ((dim == 0 && actualPoint.getX() < point.getX()) || (dim == 1 && actualPoint.getY() < point.getY())) {
            searchRangeRadius(root.left, point, depth + 1, radius, listPoints);
            searchRangeRadius(root.right, point, depth + 1, radius, listPoints);
        } else {
            searchRangeRadius(root.right, point, depth + 1, radius, listPoints);
            searchRangeRadius(root.left, point, depth + 1, radius, listPoints);
        }
    }


    public List<Point2D> searchRange(Point2D pointLeft, Point2D pointRight) {
        List<Point2D> listPoints = new ArrayList<>();
        searchRange(root, pointLeft, pointRight, 0, listPoints);
        return listPoints;
    }

    private void searchRange(Node root, Point2D pointLeft, Point2D pointRight, int depth, List<Point2D> listPoints) {
        if (root == null) {
            return;
        }

        var actualPoint = root.key;
        var x = actualPoint.getX();
        var y = actualPoint.getY();


        if (x >= pointLeft.getX() && x <= pointRight.getX() && y >= pointLeft.getY() && y <= pointRight.getY()) {
            listPoints.add(actualPoint);
        }

        int dim = depth % dimension;

        if (dim == 0) {
            if (x >= pointLeft.getX()) {
                searchRange(root.left, pointLeft, pointRight, depth + 1, listPoints);
            }
            if (x <= pointRight.getX()) {
                searchRange(root.right, pointLeft, pointRight, depth + 1, listPoints);
            }
        } else {
            if (y >= pointLeft.getY()) {
                searchRange(root.left, pointLeft, pointRight, depth + 1, listPoints);
            }
            if (y <= pointRight.getY()) {
                searchRange(root.right, pointLeft, pointRight, depth + 1, listPoints);
            }
        }
    }


    @Getter
    public static class Point2D {

        private double x, y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static double distance(Point2D p1, Point2D p2) {
            double dx = p1.getX() - p2.getX();
            double dy = p1.getY() - p2.getY();
            return Math.sqrt(dx * dx + dy * dy);
        }

    }

    public static void main(String[] args) {


        List<Point2D> listPoints = new ArrayList<>();

        listPoints.add(new Point2D(2, 3));
        listPoints.add(new Point2D(5, 4));
        listPoints.add(new Point2D(9, 6));
        listPoints.add(new Point2D(4, 7));
        listPoints.add(new Point2D(8, 1));
        listPoints.add(new Point2D(7, 2));


        KdTree_ kdTree = new KdTree_(listPoints);
//        kdTree.insert(new Point2D(2, 3));
//        kdTree.insert(new Point2D(5, 4));
//        kdTree.insert(new Point2D(9, 6));
//        kdTree.insert(new Point2D(4, 7));
//        kdTree.insert(new Point2D(8, 1));
//        kdTree.insert(new Point2D(7, 2));

        // Adicione alguns pontos à KDTree

        // Ponto de consulta
        Point2D consultPoint = new Point2D(5, 5);

        // Busca pelo vizinho mais próximo
        Point2D nearestNeighbor = kdTree.searchNeighbour(consultPoint);  // Supondo que você tenha um método getRoot()

        if (nearestNeighbor != null) {
            System.out.println("Vizinho mais próximo: (" + nearestNeighbor.getX() + ", " + nearestNeighbor.getY() + ")");
        } else {
            System.out.println("Nenhum vizinho encontrado.");
        }
    }
}
