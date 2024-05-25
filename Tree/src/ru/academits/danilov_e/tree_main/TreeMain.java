package ru.academits.danilov_e.tree_main;

import ru.academits.danilov_e.tree.BinarySearchTree;

import java.util.function.Consumer;

public class TreeMain { // TODO: 4, 5, 6, 7, 8, 9, 10, 11, 12
    public static void main(String[] args) {
        Consumer<Integer> printer = x -> System.out.printf("%d ", x);

        int number1 = 14;
        System.out.println("1. Создаем бинарное дерево, состоящее из корня " + number1 + ":");
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(number1);
        System.out.println("Результат:");
        tree.startDepthVisit(printer);
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
        tree.startDepthVisit(printer);
        System.out.println();
        System.out.println("Результат рекурсивный обход в глубину:");
        tree.startRecursivelyDepthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("Результат обход в ширину:");
        tree.startWidthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("3. Бинарный поиск:");
        System.out.println("Поиск числа 14: " + tree.contains(14));
        System.out.println("Поиск числа 5: " + tree.contains(5));
        System.out.println("Поиск числа 12: " + tree.contains(12));
        System.out.println();

        System.out.println("4. Удаление отсутствующего узла:");
        System.out.println("Результат: " + tree.remove(6));
        tree.startDepthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("5. Удаление узла с двумя потомками:");
        System.out.println("Результат: " + tree.remove(10));
        tree.startWidthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("6. Удаление корневого узла:");
        System.out.println("Результат: " + tree.remove(14));
        tree.startWidthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("7. Удаление узла с одним потомком:");
        System.out.println("Результат: " + tree.remove(13));
        tree.startWidthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();

        System.out.println("8. Удаление узла без потомка:");
        System.out.println("Результат: " + tree.remove(22));
        tree.startWidthVisit(printer);
        System.out.println("Число узлов: " + tree.size());
        System.out.println();
    }
}