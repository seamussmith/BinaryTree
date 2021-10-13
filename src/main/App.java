package main;

public class App
{
    public static void main(String[] args) 
    {
        var tree = new BinaryTree<Integer>(1);
        tree.head.left = tree.new Node(2);
        tree.head.right = tree.new Node(3);
        tree.printPreorder();
    }
}
