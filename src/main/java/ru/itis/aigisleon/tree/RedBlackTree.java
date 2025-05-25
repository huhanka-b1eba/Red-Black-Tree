package ru.itis.aigisleon.tree;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>>{

    private Node<T> root;
    private final Node<T> nil;

    public RedBlackTree() {
        nil = new Node<>(null);
        nil.color = Color.BLACK;
        nil.left = nil;
        nil.right = nil;
        nil.parent = nil;
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

    public Node<T> search(T value) {
        return search(root, value);
    }


    // Удаление
    public void delete(T value) {
        Node<T> z = search(root, value);
        if (z == nil) return;

        Node<T> y = z;
        Color yOriginalColor = y.color;
        Node<T> x;

        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;

            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == Color.BLACK) {
            deleteFixup(x);
        }
    }

    public void deleteFixup(Node<T> x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                Node<T> w = x.parent.right;

                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }

                if ((w.left == nil || w.left.color == Color.BLACK) &&
                        (w.right == nil || w.right.color == Color.BLACK)) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right == nil || w.right.color == Color.BLACK) {
                        if (w.left != nil) {
                            w.left.color = Color.BLACK;
                        }
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }

                    if (w != nil) {
                        w.color = x.parent.color;
                    }
                    x.parent.color = Color.BLACK;
                    if (w.right != nil) {
                        w.right.color = Color.BLACK;
                    }
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node<T> w = x.parent.left;

                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                if ((w.right == nil || w.right.color == Color.BLACK) &&
                        (w.left == nil || w.left.color == Color.BLACK)) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left == nil || w.left.color == Color.BLACK) {
                        if (w.right != nil) {
                            w.right.color = Color.BLACK;
                        }
                        w.color = Color.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }

                    if (w != nil) {
                        w.color = x.parent.color;
                    }
                    x.parent.color = Color.BLACK;
                    if (w.left != nil) {
                        w.left.color = Color.BLACK;
                    }
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }


    // Обход in-order
    public List<T> inOrderTraversal(){
        List<T> sorted = new ArrayList<>();
        inOrderTraversal(root, sorted);
        return sorted;
    }

    private void inOrderTraversal(Node<T> node, List<T> list) {
        if (node == nil) return;
        inOrderTraversal(node.left, list);
        list.add(node.values);
        inOrderTraversal(node.right, list);
    }

    // Дополнительные методы

    public Node<T> getNil() {
        return nil;
    }

    // минимум
    private Node<T> minimum(Node<T> x) {
        while (x.left != nil) {
            x = x.left;
        }
        return x;
    }

    // замена одного поддерева другим
    private void transplant(Node<T> u, Node<T> v) {
        if (u.parent == nil) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public boolean contains(T key) {
        return search(root, key) != nil;
    }

    public boolean isEmpty() {
        return root == nil;
    }
}
