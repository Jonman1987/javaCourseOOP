package ru.academits.danilov_e.graph_main;

import ru.academits.danilov_e.graph.Graph;

import java.util.function.Consumer;

public class GraphMain {
    public static void main(String[] args) {
        int[][] array = {
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 0},
        };

        Graph graph = new Graph(array);

        Consumer<Integer> printer = x -> System.out.printf("%d ", x);

        System.out.println("Проход по графу в глубину:");
        System.out.println("Первая компонента:");
        graph.depthVisit(0, printer);
        System.out.println();
        System.out.println("Вторая компонента:");
        graph.depthVisit(4, printer);
        System.out.println();
        System.out.println("Третья компонента:");
        graph.depthVisit(5, printer);
        System.out.println();
        System.out.println();

        System.out.println("Проход по графу в ширину:");
        System.out.println("Первая компонента:");
        graph.widthVisit(0, printer);
        System.out.println();
        System.out.println("Вторая компонента:");
        graph.widthVisit(4, printer);
        System.out.println();
        System.out.println("Третья компонента:");
        graph.widthVisit(5, printer);
        System.out.println();
        System.out.println();

        System.out.println("Рекурсивный проход по графу в глубину:");
        System.out.println("Первая компонента:");
        graph.recursivelyDepthVisit(0, printer);
        System.out.println();
        System.out.println("Вторая компонента:");
        graph.recursivelyDepthVisit(4, printer);
        System.out.println();
        System.out.println("Третья компонента:");
        graph.recursivelyDepthVisit(5, printer);
        System.out.println();
        System.out.println();

        System.out.println("Рекурсивный проход по графу в ширину:");
        System.out.println("Первая компонента:");
        graph.recursivelyWidthVisit(0, printer);
        System.out.println();
        System.out.println("Вторая компонента:");
        graph.recursivelyWidthVisit(4, printer);
        System.out.println();
        System.out.println("Третья компонента:");
        graph.recursivelyWidthVisit(5, printer);
        System.out.println();
    }
}