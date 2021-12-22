package HW_06;

public interface Tree <E extends Comparable < ? super E>> {

    enum TraversMode {  // метод который будет выбирать одн из способов обхода дерева
        IN_ORDER, PRE_ORDER, POST_ORDER
    }

    boolean add(E value);  // добавление нового узла

    boolean contains(E value);  // наличие узла

    boolean remove(E value); // удаление

    boolean isEmpty();  // проверка на пустоту

    int size();  // размер

    int level();  // уровни

    void display(); // вывод

    default void traverse(TraversMode mode) {

    }

}
