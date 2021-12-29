package HW_06;

public class Test {

    public static void main(String[] args) {
        int countTree = 0;

        int count = 0;
        while (countTree < 20) {
//            RB_Tree();
            Tree<Integer> tree = new TreeImpl<>();
            int min = -25;
            int max = 25;
            int nodeCount = 0;
            while (nodeCount < 10) {
                tree.add((int) (Math.random() * ((max - min) + 1) + min));
                nodeCount++;
            }
            countTree++;

            count = 0;
            for (int i = 0; i < countTree; i++) {
                count += tree.balance() ? 1 : 0;
            }

            tree.display();
        }
        System.out.print(String.format("%s %d", "Total trees count:", countTree));
        System.out.println(String.format("%s %d%s", "Balanced:", count * 20 / countTree, "%"));

    }
}
