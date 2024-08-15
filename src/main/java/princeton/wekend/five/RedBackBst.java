package princeton.wekend.five;

import static edu.princeton.cs.algs4.StdOut.println;

public class RedBackBst<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;


    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;

        private int count;

        public Node(Key key, Value value, boolean color,int count) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.count = count;
        }

    }

    private boolean isRed(Node node) {
        if (node == null)
            return false;
        return node.color == RED;
    }


    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    private Node insert(Node node, Key key, Value value) {

        if (node == null)
            return new Node(key, value, RED,1);

        int newKeyCompare = key.compareTo(node.key);

        if (newKeyCompare < 0) {
            node.left = insert(node.left, key, value);
        } else if (newKeyCompare > 0) {
            node.right = insert(node.right, key, value);
        } else
            node.value = value;


        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        } else if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        } else if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        node.count = size(node.left) + size(node.right) + 1;

        return node;
    }


    public int rank(Key key) {
        return rank(root, key);
    }


    public int rank(Key keyLow, Key keyHigh) {
        if(contains(keyHigh)) {
            return rank(keyHigh) - rank(keyLow) ;
        } else {
            return rank(keyHigh) - rank(keyLow);
        }
    }


    private int rank(Node node, Key key) {
        if (node == null) {
            return 0; // Key not found
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return rank(node.left, key);
        } else if (compare > 0) {
            return size(node.left) + 1 + rank(node.right, key);
        } else {
            // Key found, return its rank (number of keys in left subtree)
            return size(node.left);
        }
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.count;
        }
    }
    public void printTree( ) {
        printTree( root.right );
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( Node t ) {
        if( t != null ) {
            printTree( t.left );
            System.out.println( t.key );
            printTree( t.right );
        }
    }


    private Node rotateLeft(Node node) {
        Node nodeRight = node.right;
        node.right = nodeRight.left;
        nodeRight.left = node;
        nodeRight.color = node.color;
        node.color = RED;
        // Atualizando a contagem de nós
        node.count = size(node.left) + size(node.right) + 1;
        nodeRight.count = size(nodeRight.left) + size(nodeRight.right) + 1;

        return nodeRight;
    }

    private Node rotateRight(Node node) {
        Node nodeLeft = node.left;
        node.left = nodeLeft.right;
        nodeLeft.right = node;
        nodeLeft.color = node.color;
        node.color = RED;
        // Atualizando a contagem de nós
        node.count = size(node.left) + size(node.right) + 1;
        nodeLeft.count = size(nodeLeft.left) + size(nodeLeft.right) + 1;

        return nodeLeft;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }




    private boolean contains(Key key) {
        return get(root, key) != null;
    }

    public Value get(Key key) {
        Node root = get(this.root, key);
        if (root == null)
            return null;
        return root.value;
    }

    private Node get(Node node, Key key) {
        if (node == null)
            return null;
        int newKeyCompare = key.compareTo(node.key);

        if (newKeyCompare < 0)
            return node.left = get(node.left, key);
        else if (newKeyCompare > 0)
            return node.right = get(node.right, key);
        else
            return node;


    }




    public static void main(String[] args) {

        RedBackBst<Integer, String> redBackBst = new RedBackBst<>();

        redBackBst.insert(6, "6");
        redBackBst.insert(4, "4");
        redBackBst.insert(5, "5");
        redBackBst.insert(10, "5");
        redBackBst.insert(1, "5");
        redBackBst.insert(3, "5");
        redBackBst.insert(7, "5");
        redBackBst.insert(2, "5");
        redBackBst.insert(9, "5");

        println(redBackBst.rank(1,5));
    }


}
