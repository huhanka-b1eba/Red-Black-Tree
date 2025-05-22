package aa.tulybaev;

public class Node<T> {

    public T values;

    public Color color;

    public Node<T> left;

    public Node<T> right;

    public Node<T> parent;

    public Node(T values) {
        this.values = values;
        this.color = Color.RED; // Новые узлы всегда красные
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Node(T values, Node<T> left, Node<T> right, Node<T> parent) {
        this.values = values;
        this.color = Color.RED;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
