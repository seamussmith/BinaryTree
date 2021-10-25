package main;

import java.util.Arrays;

public class App
{
    public static void main(String[] args) 
    {
        var tree = new TreeSet<Integer>();
        tree.add(1);
        tree.add(-2);
        tree.add(2);
        tree.add(-3);
        tree.add(-1);
        System.out.println(tree.containsAll(Arrays.asList(2, -3)));
        System.out.println(tree.contains(-2));
        tree.printInorder();
    }
}
