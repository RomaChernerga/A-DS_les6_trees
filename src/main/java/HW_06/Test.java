package HW_06;


public class Test {

    public static void main(String[] args) {

        int countTree = 0;
        while (countTree < 20) {
            RB_Tree();
            countTree++;
        }

    }

    private static void RB_Tree() {
        Tree<Integer> tree = new TreeImpl<>();
        int min = -25;
        int max = 25;
        int nodeCount = 0;
        while (nodeCount < 10) {
            tree.add((int) (Math.random() * ((max-min)+1)+min));
            nodeCount++;
        }
        tree.display();
        System.out.println("Размер дерева равен " + "'" +  tree.size() + "'");
        System.out.println();

    }
}
