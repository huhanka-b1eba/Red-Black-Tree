package aa.tulybaev;

public class RedBlackTree<T extends Comparable<T>>{

    private Node<T> root;
    private final Node<T> nil;

    public RedBlackTree() {
        nil = new Node<>(null);
        nil.color = Color.BLACK;
        root = nil;
    }

    // Повороты
    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;

        if (y.left != nil) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node<T> y) {
        Node<T> x = y.left;
        y.left = x.right;

        if (x.right != nil) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == nil) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    // Вставка
    public void insert(T value) {
        Node<T> z = new Node<>(value);
        Node<T> y = nil;
        Node<T> x = root;

        while(x != nil) {
            y = x;
            if (z.values.compareTo(x.values) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.parent = y;
        if (y == nil) {
            root = z;
        } else if (z.values.compareTo(y.values) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.left = nil;
        z.right = nil;
        z.color = Color.RED;

        insertFixup(z);
    }

    public void insertFixup(Node<T> z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                Node<T> y = z.parent.parent.right;

                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }

                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z);
                }
            } else {
                Node<T> y = z.parent.parent.left;

                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    // Поиск узла
    private Node<T> search(Node<T> x, T value) {
        if (x == nil || value.equals(x.values)) {
            return x;
        }

        if (value.compareTo(x.values) < 0) {
            return search(x.left, value);
        } else {
            return search(x.right, value);
        }
    }


    // Удаление
    // ...

    // Дополнительные методы
    private Node<T> minimum(Node<T> x) {
        while (x.left != nil) {
            x = x.left;
        }
        return x;
    }

    public boolean contains(T key) {
        return search(root, key) != nil;
    }

    public boolean isEmpty() {
        return root == nil;
    }
}
