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

        tree.add(4);
        tree.add(22);
        tree.add(3);
        tree.add(6);
        tree.add(5);
        tree.add(15);

        System.out.println("Результат обход в глубину:");
        tree.deepTreeShow();
        System.out.println("Число элементов: " + tree.size());
        System.out.println();

        System.out.println("Результат обход в ширину:");
        tree.widthTreeShow();
        System.out.println("Число элементов: " + tree.size());
        System.out.println();
    }
}