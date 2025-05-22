package aa.tulybaev;

public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);

        System.out.println(tree.contains(20));
        System.out.println(tree.contains(40));
    }
}