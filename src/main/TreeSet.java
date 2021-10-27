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

    class TreeSetTraverser
    {
        Stack<Node> nodes = new Stack<>();
        Node curr = TreeSet.this.head;
        void goLeft()
        {
            nodes.push(curr);
            curr = curr.left;
        }
        void goRight()
        {
            nodes.push(curr); 
            curr = curr.right;
        }
        void goBack()
        {
            curr = nodes.pop();
        }
    }
    @Override
    public Iterator iterator()
    {
        return new TreeSetIterator();
    }
    public class TreeSetIterator implements ListIterator<TElement>
    {
        Stack<Node> nodes = new Stack<>();
        Node curr;
        public TreeSetIterator()
        {
            nodes.push(TreeSet.this.head);
            while (nodes.peek().left != null)
            {
                nodes.push(nodes.peek().left);
            }
        }
        @Override
        public boolean hasNext()
        {
            return !nodes.isEmpty();
        }
        @Override
        public TElement next()
        {
            var pop = nodes.pop();
            var leftpop = pop.left;
            if (pop.right != null)
            {
                leftpop = pop.right;
                while (leftpop != null)
                {
                    nodes.push(leftpop);
                    leftpop = leftpop.left;
                }
            }
            curr = pop;
            return pop.data;
        }
        @Override
        public boolean hasPrevious()
        {
            // TODO Auto-generated method stub
            return false;
        }
        @Override
        public TElement previous()
        {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public int nextIndex()
        {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public int previousIndex()
        {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public void remove()
        {
            TreeSet.this.remove(curr);
        }
        @Override
        public void set(TElement e) {
            throw new UnsupportedOperationException("Cannot set node in a TreeSet");
        }
        @Override
        public void add(TElement e) {
            throw new UnsupportedOperationException("Cannot set node in a TreeSet");
        }

    }
}