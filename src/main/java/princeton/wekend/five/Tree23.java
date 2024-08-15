package princeton.wekend.five;

public class Tree23<Key extends Comparable<Key>, Value> {

    private Node root;

    public class Node {
        private Key keyLeft, keyRight,keyMiddle;
        private Value valueLeft, valueRight,valueMiddle;
        private Node left, middle, right;

        public Node(Key key, Value value) {
            this.keyLeft = key;
            this.valueLeft = value;
        }

        public boolean isTwoNode() {
            return keyRight == null;
        }

        public boolean isThreeNode() {
            return keyRight != null;
        }

        public boolean isLeaf() {
            return left == null && middle == null && right == null;
        }
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (node.isTwoNode()) {
            if (key.compareTo(node.keyLeft) < 0) {
                node.keyRight = node.keyLeft;
                node.valueRight = node.valueLeft;
                node.keyLeft = key;
                node.valueLeft = value;
            } else if (key.compareTo(node.keyLeft) > 0) {
                node.keyRight = key;
                node.valueRight = value;
            } else {
                // Duplicate key, update value
                node.valueLeft = value;
            }
        }else if(node.isThreeNode()){

            int compareLeft = key.compareTo(node.keyLeft);
            int compareRight = key.compareTo(node.keyRight);

            if (compareLeft < 0) {
                node.left = insert(node.left, key, value);
            } else if (compareLeft > 0 && compareRight < 0) {
                node.keyMiddle = key;
                node.valueMiddle = value;
            } else if (compareRight > 0) {
                node.right = insert(node.right, key, value);
            } else {
                // Duplicate key, update value
                node.valueLeft = value;
            }
        }


        return node;
    }

    private Node split(Node node) {
        Node newLeft = new Node(node.keyLeft, node.valueLeft);
        Node newRight = new Node(node.keyRight, node.valueRight);

        newLeft.left = node.left;
        newLeft.middle = node.middle;
        newRight.left = node.right;

        node.keyLeft = node.keyRight = null;
        node.valueLeft = node.valueRight = null;
        node.left = node.middle = node.right = null;

        node.keyLeft = newLeft.keyLeft;
        node.valueLeft = newLeft.valueLeft;
        node.keyRight = newRight.keyLeft;
        node.valueRight = newRight.valueLeft;

        node.left = newLeft.left;
        node.middle = newLeft.middle;
        node.right = newRight.left;

        return node;
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int level) {
        if (node != null) {
            printTree(node.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println("(" + node.keyLeft + ", " + node.keyRight + ")");
            printTree(node.middle, level + 1);
            printTree(node.left, level + 1);
        }
    }

    public static void main(String[] args) {
        Tree23<Integer, String> tree = new Tree23<>();

        tree.insert(10, "Value 10");
        tree.insert(20, "Value 20");
        tree.insert(30, "Value 30");
        tree.insert(40, "Value 40");
        tree.insert(50, "Value 50");

        tree.printTree();
    }
}
