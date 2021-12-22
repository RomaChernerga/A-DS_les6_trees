package HW_06;

import java.util.Stack;

public class TreeImpl <E extends Comparable <? super E>>implements Tree<E> {

    private Node<E> root; // корень дерева
    private int size;  //размер дерева
    private int level;  //размер дерева

    private class NodeAndParent {  //метод для сохранения ссылок на узлы
        private Node<E> current;
        private Node<E> parent;
        private int level;


        public NodeAndParent(Node<E> current, Node<E> parent, int level) {
            this.current = current;
            this.parent = parent;
            this.level = level;
        }
    }

    private NodeAndParent doFind(E value) {
        Node<E> current = root;
        Node<E> parent = null;
        int level = 0;

        while (current != null) {  // пробегаемся по дереву, до самого нижнего листа
            if(current.getValue().equals(value)) { // сравниваем первый узел с тем, что мы ищем
                return new NodeAndParent(current, parent, level); // ели да, то это корень
            }
            parent = current; //если корень дерева - не искомый объект, то спускаемся ниже и переопределяем паррент

            if(current.isLeftChild(value)) {  // если мы все таки идем по левой стороне дерева
                // берем левый дочерний узел от того, что проверяли до этого и находим ссылку на лев дочерний метод и присваиваем ее переменной карент
                current = parent.getLeftChild();
            } else {
                current = current.getRightChild(); // в противном случае двигаемся в противоположн. сторону дерева
            }
        }
        return new NodeAndParent(null, parent, level); // возвращаем налл и что было с предыдущим карентом
    }

    @Override
    public boolean contains(E value) {  // проверка на наличие узла
        NodeAndParent nodeAndParent = doFind(value);
        return nodeAndParent.current != null;
    }

    @Override
    public boolean add(E value) {
        NodeAndParent nodeAndParent = doFind(value); // сперва положение для нового узла

        if(nodeAndParent.current != null) { // если
//            repeat++;
            return false;
        }

        Node<E> parent = nodeAndParent.parent;
        Node<E> node = new Node<>(value);  // создаем новый узел который заложим в дерево

        if(isEmpty()) {  // проверка, на случай, если дерево пустое
            root = node;

            // теперь мы понимаем родителя и узел, куда нужно создавать...
        } else if(parent.isLeftChild(value)) { // если парент должен быть добавлен в лев сторону, то обращаемся к род узлу
            parent.setLeftChild(node); // и отдаем узел, который должен там быть
            level++;
        } else {
            parent.setRightChild(node); // а иначе присваиваем правую сторону
            level++;
        }
        size++;

        return true;
    }


    @Override
    public boolean remove(E value) {

        NodeAndParent nodeAndParent = doFind(value);  // сперва находим элемент, который будем удалять
        Node<E> removed = nodeAndParent.current;
        Node<E> parent = nodeAndParent.parent;

        if (removed == null) {  // если удаляемого узла нет, то возвращаем false
            return false;
        }
        // 1 способ - если удаляемый нами узел является листом
        if (removed.isLeaf()){
            removedNodeWithoutChildren(removed, parent);
            // 2 способ -
        } else if(removed.hasOnlyOneChild()){ //проверка, а имеет ли узел хотя бы один дочерний элемент
            removeNodeWithOneChild(removed, parent);
            // 3 вариант - если у нас 2 дочерних узла при удалении
        } else {
            removeNodeWithAllChildren(removed, parent);
        }
        size--;
        return true;
    }

    private void removeNodeWithAllChildren(Node<E> removed, Node<E> parent) {
        //  [1 2 3 4] 5 [6 7 8 9 10]
       Node<E> successor = getSuccessor(removed);   //ищем замену удаляемому элементу
        if (removed == root) {
            root = successor;
        } else if (parent.isLeftChild(removed.getValue())) {
            parent.setLeftChild(successor);
        } else {
            parent.setRightChild(successor);
        }
    }

    private Node<E> getSuccessor(Node<E> removed) {
        Node<E> successor = removed;  // по умолчанию
        Node<E> successorParent = null; // родительский
        Node<E> current = removed.getRightChild();  // будем искать крайний элемент из правой части дерева

        while (current  != null) { // пока не упремся в самый крайний элемент при поиске
            successorParent = successor; // родительским элементом будет тот, кто является сейчас по умолчанию
            successor = current;  // а по умолчанию станет тот, кто карент
            current = current.getLeftChild(); // а правым - будет тот, кто стоит с правой стороны
        }

        if (successor != removed.getRightChild() && successorParent != null) { //проверка, что поисковая замена не равняется узлу с правой стороны
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removed.getRightChild()); // переносим наверх замену
        }
            successor.setLeftChild(removed.getLeftChild());

        return successor;
    }

    private void removeNodeWithOneChild(Node<E> removed, Node<E> parent) {
        // сперва определяем, дочерний элемент удаляемого элемента правый или левый
//        CW_06.Node<E> child = removed.getLeftChild() != null ?  // проверяем наличие узлов
//                    removed.getLeftChild(): //ставим в левую сторону
//                    removed.getRightChild();  //в противном случае в правую
        //  АЛЬТЕРНАТИВНАЯ ЗАПИСЬ
        Node<E> child = null;
        if(removed.getLeftChild() != null) {
            child = removed.getLeftChild();
        } else {
            child = removed.getRightChild();
        }


        if (removed == root) { // проверка на то, если удаляемый элемент является корнем
            root = child;
        }

        if (parent.isLeftChild(removed.getValue())) { // если удаляемый узел стоит слева
            parent.setLeftChild(child); // отправляем ему данные дочернего узла
        } else {
            parent.setRightChild(child); // в противном случае отправляем данные доч узла направо
        }
    }

    // УДАЛЕНИЕ ЭЛЕМЕНТА С ОДНИМ ДОЧЕРНИМ ЭЛЕМЕНТОМ
    private void removedNodeWithoutChildren(Node<E> removed, Node<E> parent) {
        if(removed == root) {
            root = null; // уточняем, является ли удаляемый узел корнем, тогда корень = null
        }
        else if(parent.isLeftChild(removed.getValue())) { //если удаляемый элемент находится слева
            parent.setLeftChild(null); // обнуляем левый дочерний элемент
        } else {
            parent.setRightChild(null); // обнуляем правый дочерний элемент
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int level() {
        return level();
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlank = 64;

        boolean isRowEmpty = false;
        System.out.println("-------------------------------");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlank; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlank * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
            nBlank /= 2;
        }
        System.out.println("................................................................");
    }

    @Override
    public void traverse(TraversMode mode) {
        switch (mode) {
            case PRE_ORDER -> preOrder(root); // прямой обход
            case IN_ORDER -> inOrder(root); // центрированный обход по порядку
            case POST_ORDER -> postOrder(root); // обратный обход
        }

    }

    private void preOrder(Node<E> current) {  // Метод прямого обхода
        if(current == null) {
            return;
        }
        System.out.println(current.getValue());
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }

    private void inOrder(Node<E> current) {  // Метод центрованного обхода
        if(current == null) {
            return;
        }
        inOrder(current.getLeftChild());
        System.out.println(current.getValue());
        inOrder(current.getRightChild());
    }

    private void postOrder(Node<E> current) {  // Метод центрованного обхода
        if(current == null) {
            return;
        }
        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.println(current.getValue());
    }
}
