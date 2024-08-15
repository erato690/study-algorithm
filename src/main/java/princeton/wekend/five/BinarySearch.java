package princeton.wekend.five;

import edu.princeton.cs.algs4.Queue;
import lombok.Value;

public class BinarySearch<Key extends Comparable<Key>, Value> {

    private Node root;

    public void insert(Key key, Value value) {

        root = insert(root, key, value);
    }

    private Node insert(Node node, Key key, Value value) {

        if (node == null)
            return new Node(key, value,1);

        int newKeyCompare = key.compareTo(node.key);

        if (newKeyCompare < 0)
            node.left = insert(node.left, key, value);
        else if (newKeyCompare > 0)
            node.right = insert(node.right, key, value);
        else
            node.value = value;

        node.count = 1 + size(node.left) + size(node.right);

        return node;
    }

    public Key floor(Key key) {
        Node root = floor(this.root, key);
        if (root == null)
            return null;
        return root.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null)
            return null;

        int newKeyCompare = key.compareTo(node.key);

        if (newKeyCompare == 0)
            return node;

        if (newKeyCompare < 0)
            return floor(node.left, key);

        Node t = floor(node.right, key);
        if (t != null)
            return t;
        else
            return node;
    }

    public Value get(Key key) {
        Node root = get(this.root, key);
        if (root == null)
            return null;
        return root.value;
    }
    private Node get(Node node ,Key key) {
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

    public void delete(Key key) {
        delete(root, key);
    }

    private Node delete(Node node, Key key) {


        int newKeyCompare = key.compareTo(node.key);

        if (newKeyCompare < 0)
            node.left = delete(node.left, key);
        else if (newKeyCompare > 0)
            node.right = delete(node.right, key);
        else {

            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node successor = node;
            node = min(successor.right);
            node.right = deleteMin(successor.right);
            node.left = successor.left;

        }
        node.count = 1 + size(node.left) + size(node.right);

        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }


    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        inOrder(root,queue);
        return queue;
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null)
            return 0;
        return root.count;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private void inOrder(Node root, Queue<Key> queue) {
        if (root == null)
            return;
        inOrder(root.left,queue);
        queue.enqueue(root.key);
        inOrder(root.right,queue);
    }

    private int rank(Node root, Key key) {
        if (root == null)
            return 0;

        int newKeyCompare = key.compareTo(root.key);

        if (newKeyCompare < 0)
            return rank(root.left, key);
        else if (newKeyCompare > 0)
            return 1 + size(root.left) + rank(root.right, key);
        else
            return size(root.left);
    }

    public Node min() {
        return min(root);
    }

    private Node min(Node root) {

        if (root.left == null)
            return root;
        return min(root.left);
    }

    public class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int count;

        public Node(Key key, Value value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }


    }
}
