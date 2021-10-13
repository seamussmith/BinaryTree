package main;

public class BinaryTree<TElement>
{
    public Node head = new Node();
    public BinaryTree()
    {
    }
    public BinaryTree(TElement data)
    {
        this.head.data = data;
    }
    public void printPreorder()
    {
        head.printPreorder();
    }
    public class Node
    {
        public TElement data;
        public Node left;
        public Node right;
        public Node()
        {
        }
        public Node(TElement data)
        {
            this.data = data;
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
