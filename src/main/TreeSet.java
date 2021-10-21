package main;

import java.security.InvalidAlgorithmParameterException;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

public class TreeSet<TElement extends Comparable<TElement>> extends AbstractSet
{
    public Node head;
    private int size = 0;
    public int size() { return size; }

    public void printInorder()
    {
        if (head != null)
            head.printInorder();
    }
    @Override
    public void clear()
    {
        head = null;
    }

    @Override
    public boolean add(Object e)
    {
        var element = (TElement)e;
        if (head == null)
        {
            head = new Node(element);
            return true;
        }
        Node prev = null;
        Node curr = head;
        boolean isLeft = false;
        while (true)
        {
            if (curr == null)
            {
                if (isLeft)
                    prev.left = new Node(element);
                else
                    prev.right = new Node(element);
                return true;
            }
            // If you insert null into my tree, screw you
            var comp = element.compareTo(curr.data);
            prev = curr;
            if (comp == 0)
            {
                return false;
            }
            else if (comp > 0)
            {
                isLeft = false;
                curr = curr.right;
            }
            else if (comp < 0)
            {
                isLeft = true;
                curr = curr.left;
            }
        }
    }

    private class Node
    {
        Node left;
        Node right;
        TElement data;
        void printInorder()
        {
            if (left != null)
                left.printInorder();
            System.out.println(data);
            if (right != null)
                right.printInorder();
        }
        Node(Node left, Node right, TElement data)
        {
            this.left = left;
            this.right = right;
            this.data = data;
        }
        Node(TElement data)
        {
            this.data = data;
        }
    }

    @Override
    public Iterator iterator()
    {

        return null;
    }
}