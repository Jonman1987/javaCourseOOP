package ru.academits.danilov_e.tree_main;

import ru.academits.danilov_e.tree.BinarySearchTree;

import java.util.TreeSet;
import java.util.function.Consumer;

public class TreeMain {
    public static void main(String[] args) {
        Consumer<Integer> printer = x-> System.out.printf("%d ", x);

        int number1 = 14;
        System.out.println("1. Создаем бинарное дерево, состоящее из корня " + number1 + ":");
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(number1);
        System.out.println("Результат:");
        tree.depthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("2. Наполняем дерево узлами:");

        tree.add(10);
        tree.add(22);
        tree.add(3);
        tree.add(13);
        tree.add(11);
        tree.add(15);
        tree.add(12);

        System.out.println("Результат обход в глубину:");
        tree.depthVisit(printer);
        System.out.println();
        System.out.println("Результат рекурсивный обход в глубину:");
        tree.recursivelyDepthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("Результат обход в ширину:");
        tree.widthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("3. Бинарный поиск:");
        System.out.println("Поиск числа 14: " + tree.contains(14));
        System.out.println("Поиск числа 5: " + tree.contains(5));
        System.out.println("Поиск числа 12: " + tree.contains(12));
        System.out.println();

        System.out.println("4. Удаление отсутствующего узла:");
        System.out.println("Результат: " + tree.remove(6));
        tree.depthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("5. Удаление узла с двумя потомками:");
        System.out.println("Результат: " + tree.remove(10));
        tree.widthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("6. Удаление корневого узла:");
        System.out.println("Результат: " + tree.remove(14));
        tree.widthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("7. Удаление узла с одним потомком:");
        System.out.println("Результат: " + tree.remove(13));
        tree.widthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("8. Удаление узла без потомка:");
        System.out.println("Результат: " + tree.remove(22));
        tree.widthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        /*BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(6);
        System.out.println("Число узлов: " + tree2.size());
        tree2.add(10);
        System.out.println("Число узлов: " + tree2.size());
        tree2.add(13);
        System.out.println("Число узлов: " + tree2.size());*/

        TreeSet<Integer> df = new TreeSet<>();
        df.add(34);
        df.add(22);
        System.out.println(df.getFirst().compareTo(11));

        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(6);
        tree2.add(34);
        tree2.add(32);
    }
}