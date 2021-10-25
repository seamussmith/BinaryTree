package main;

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
        tree.remove(1);
        System.out.println(tree.contains(-2));
        tree.printInorder();
    }
}
