package ru.academits.danilov_e.tree_main;

import ru.academits.danilov_e.tree.BinarySearchTree;

public class TreeMain {
    public static void main(String[] args) {
        int number1 = 14;
        System.out.println("1. Создаем бинарное дерево, состоящее из корня " + number1 + ":");
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(number1);
        System.out.println("Результат:");
        tree.deepTreeShow();
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
        tree.deepTreeShow();
        System.out.println("Число элементов: " + tree.size());
        System.out.println();

        System.out.println("Результат обход в ширину:");
        tree.widthTreeShow();
        System.out.println("Число элементов: " + tree.size());
        System.out.println();

        System.out.println("3. Бинарный поиск:");
        System.out.println("Поиск числа 14: " + tree.binarySearch(14));
        System.out.println("Поиск числа 5: " + tree.binarySearch(5));
        System.out.println("Поиск числа 10: " + tree.binarySearch(10));
        System.out.println();

        System.out.println("4. Удаление отсутствующего узла:");
        tree.remove(6);
        tree.deepTreeShow();
        System.out.println();

        System.out.println("5. Удаление узла с двумя потомками:");
        tree.remove(10);
        tree.widthTreeShow();
        System.out.println();

        System.out.println("6. Удаление корневого узла:");
        tree.remove(14);
        tree.widthTreeShow();
        System.out.println();

        System.out.println("7. Удаление узла с одним потомком:");
        tree.remove(13);
        tree.widthTreeShow();
        System.out.println();

        System.out.println("8. Удаление узла без потомка:");
        tree.remove(22);
        tree.widthTreeShow();
        System.out.println();
    }
}