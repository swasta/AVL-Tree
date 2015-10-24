import java.util.LinkedList;
import java.util.Queue;


public class AVLTree {
    private Node root;
    private int size = 0;

    public AVLTree() {
        this.root = null;
    }

    public int size() {
        return this.size;
    }

    public int treeHeight() {
        return getHeight(root);
    }

    public void add(Comparable data) {
        root = add(root, data);
        size++;
    }

    public void delete(Comparable x) {
        root = delete(root, x);
        size--;
    }

    public Comparable find(Comparable x) {
        return elementAt(find(x, root));
    }

    public void print() {
        //preOrder(root);
        levelOrderPrint(root);
    }

    private Node add(Node node, Comparable data) {
        if (node == null) {
            return new Node(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = add(node.left, data);
        } else {
            node.right = add(node.right, data);
        }

        node.setHeight(Math.max(getHeight(node.left), getHeight(node.right)) + 1);

        int balance = balanceFactor(node);

        // Left Left Case
        if (balance > 1 && data.compareTo(node.left.data) < 0)
            return rightRotate(node);

        // Right right Case
        if (balance < -1 && data.compareTo(node.right.data) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node find(Comparable x, Node t) {
        while (t != null) {
            if (x.compareTo(t.data) < 0)
                t = t.left;
            else if (x.compareTo(t.data) > 0)
                t = t.right;
            else
                return t;
        }
        return null;
    }

    private int balanceFactor(Node n) {
        if (n == null) {
            return 0;
        }
        return (getHeight(n.left) - getHeight(n.right));
    }

    private int getHeight(Node n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }

    private Comparable elementAt(Node t) {
        return t == null ? null : t.data;
    }

    private Node minValueNode(Node n) {
        Node current = n;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private Node delete(Node root, Comparable x) {
        if (root == null) {
            return root;
        }
        if (x.compareTo(root.data) < 0) {
            root.left = delete(root.left, x);
        } else if (x.compareTo(root.data) > 0) {
            root.right = delete(root.right, x);
        } else {
            if (root.left == null || root.right == null) {
                Node tmp = root.left == null ? root.left : root.right;
                if (tmp == null) {
                    tmp = root;
                    root = null;
                } else {
                    root = tmp;
                }
            } else {
                Node tmp = minValueNode(root.right);
                root.data = tmp.data;
                root.right = delete(root.right, tmp.data);
            }
        }
        if (root == null) {
            return root;
        }

        root.setHeight(Math.max(getHeight(root.left), getHeight(root.right)) + 1);
        int balance = balanceFactor(root);

        // Left Left Case
        if (balance > 1 && balanceFactor(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && balanceFactor(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && balanceFactor(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && balanceFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.setHeight(Math.max(getHeight(y.left), getHeight(y.right)) + 1);
        x.setHeight(Math.max(getHeight(x.left), getHeight(x.right)) + 1);

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.setHeight(Math.max(getHeight(x.left), getHeight(x.right)) + 1);
        y.setHeight(Math.max(getHeight(y.left), getHeight(y.right)) + 1);

        // Return new root
        return y;
    }

    private static void levelOrderPrint(Node root) {
        Queue<Node> que = new LinkedList<Node>();
        Node mark = new Node(0);
        if (root != null) {
            que.add(root);
            que.add(mark);
        }
        while (!que.isEmpty()) {
            Node temp = que.poll();
            if (temp != mark) {
                System.out.print(temp.data + " ");
            } else {
                if (que.isEmpty()) {
                    return;
                }
                que.add(mark);
                System.out.println();
            }
            if (temp.left != null) {
                que.add(temp.left);
            }
            if (temp.right != null) {
                que.add(temp.right);
            }
        }
    }

    private void preOrder(Node root) {
        if (root != null) {
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
}
