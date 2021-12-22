package HW_06;

import java.util.Random;

public class Test {

    public static void main(String[] args) {

        Tree<Integer> tree = new TreeImpl<>();
        int min = -25;
        int max = 25;

        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));
        tree.add((int) (Math.random() * ((max-min)+1)+min));


        tree.display();
        System.out.println("Размер дерева равен " + "'" +  tree.size() + "'");

//        tree.remove(31);
//        tree.remove(42);
//        tree.remove(70);
//        tree.display();

//        tree.traverse(CW_06.Tree.TraversMode.IN_ORDER);



    }
}
