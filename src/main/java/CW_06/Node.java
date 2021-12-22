package CW_06;

public class Node <T extends Comparable< ? super T>> {

    private T value;  //значение
    private int repeatCount;
    private boolean isRepeat;
    private boolean isRemove;

    private Node <T> leftChild;  //лев дочерний элемент
    private Node <T> rightChild;  //прав дочерний элемент

    public Node(T value) {  // конструктор
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }



    public boolean isLeftChild (T value) {  // в случае, если нужно двигаться по левой стороне дерева
        return getValue().compareTo(value) > 0; // если разница корня дерева и искомого объекта БОЛЬШЕ нуля, то идем в левую сторону
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public boolean hasOnlyOneChild() {
//        return getRightChild() == null && getLeftChild() != null
//                || getRightChild() != null && getRightChild() == null;
        return getLeftChild() != null ^ getRightChild() != null;
    }
}
