package main;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

public class TreeSet<
    TElement extends Comparable<TElement>
    >
    extends AbstractSet<TElement>
{
    private Node head;
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
        size ^= size;
    }

    @Override
    public boolean add(TElement e)
    {
        if (head == null)
        {
            head = new Node(e);
            ++size;
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
                    prev.left = new Node(e);
                else
                    prev.right = new Node(e);
                ++size;
                return true;
            }
            // If you insert null into my tree, screw you
            prev = curr;
            var comp = e.compareTo(curr.data);
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

    @Override
    public boolean remove(Object o)
    {
        var element = (Comparable<TElement>)o;
        if (head == null)
        {
            return false;
        }
        Node curr = head;
        Node prev = null;
        boolean isLeft = false;
        while (true)
        {
            if (curr == null)
            {
                return false;
            }
            // If you insert null into my tree, screw you
            var comp = element.compareTo(curr.data);
            if (comp == 0)
            {
                if (curr.right == null && curr.left == null)
                {
                    if (isLeft)
                        prev.left = null;
                    else    
                        prev.right = null;
                }
                else if (curr.left == null && curr.right != null)
                {
                    prev.right = curr.right;
                }
                else if (curr.right == null && curr.left != null)
                {
                    prev.left = curr.left;
                }
                else
                {
                    curr.data = curr.left.data;
                    element = curr.left.data;
                    prev = curr;
                    curr = curr.left;
                    isLeft = true;
                    continue;
                }
                return true;
            }
            prev = curr;
            if (comp > 0)
            {
                curr = curr.right;
                isLeft = false;
            }
            else if (comp < 0)
            {
                curr = curr.left;
                isLeft = true;
            }
        }
    }

    @Override
    public boolean contains(Object o)
    {
        var element = (Comparable<TElement>)o;
        if (head == null)
        {
            return false;
        }
        Node curr = head;
        while (true)
        {
            if (curr == null)
            {
                return false;
            }
            // If you insert null into my tree, screw you
            var comp = element.compareTo(curr.data);
            if (comp == 0)
            {
                return true;
            }
            else if (comp > 0)
            {
                curr = curr.right;
            }
            else if (comp < 0)
            {
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
        return new TreeSetItterator();
    }

    public class TreeSetItterator implements ListIterator<TElement>
    {
        Stack<Node> nodes = new Stack<>();
        public TreeSetItterator()
        {
            nodes.push(TreeSet.this.head);
        }
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }
        @Override
        public TElement next() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public boolean hasPrevious() {
            // TODO Auto-generated method stub
            return false;
        }
        @Override
        public TElement previous() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public int nextIndex() {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public int previousIndex() {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public void remove() {
            // TODO Auto-generated method stub
            
        }
        @Override
        public void set(TElement e) {
            // TODO Auto-generated method stub
            
        }
        @Override
        public void add(TElement e) {
            // TODO Auto-generated method stub
            
        }

    }
}