
public class Node<E> {
    public Comparable data;
    public Node left, right;
    public int height;


    public Node(Comparable data) {
        this.data = data;
        this.height = 1;
        this.left = this.right = null;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
