package main;

public class BinaryTree
{
    public Node head;
    public BinaryTree()
    {
    }
    public BinaryTree(int max)
    {
        this.head = buildTree(1, max);
    }
    private Node buildTree(int n, int max)
    {
        if (n > max)
            return null;
        Node left = buildTree(n * 2, max);
        Node right = buildTree(n * 2 + 1, max);
        return new Node(n, left, right);
    }
    public void printPreorder()
    {
        head.printPreorder();
    }
    public class Node
    {
        public int data;
        public Node left;
        public Node right;
        public Node()
        {
        }
        public Node(int data, Node left, Node right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        // An object oriented recursion? Don't see that all day!
        public void printPreorder()
        {
            System.out.println(data);
            if (left != null)
                left.printPreorder();
            if (right != null)
                right.printPreorder();
        }
        public boolean isLeaf()
        {
            return left == null ^ right == null;
        }
        public boolean isDeadEnd()
        {
            return left == null && right == null;
        }
        public boolean isBranch()
        {
            return !isDeadEnd();
        }
    }
}
