package ru.academits.danilov_e.graph_main;

import ru.academits.danilov_e.graph.Graph;

import java.util.function.IntConsumer;

public class GraphMain {
    public static void main(String[] args) {
        int[][] connectivityMatrix = {
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 0}
        };

        Graph graph = new Graph(connectivityMatrix);

        IntConsumer printer = x -> System.out.printf("%d ", x);

        System.out.println("Обход по графу в глубину:");
        System.out.println("Первая компонента:");
        graph.startDepthVisit(0, printer);
        System.out.println();
        System.out.println("Вторая компонента:");
        graph.startDepthVisit(4, printer);
        System.out.println();
        System.out.println("Третья компонента:");
        graph.startDepthVisit(5, printer);
        System.out.println();
        System.out.println();

        System.out.println("Рекурсивный обход по графу в глубину:");
        System.out.println("Первая компонента:");
        graph.startRecursivelyDepthVisit(0, printer);
        System.out.println();
        System.out.println("Вторая компонента:");
        graph.startRecursivelyDepthVisit(4, printer);
        System.out.println();
        System.out.println("Третья компонента:");
        graph.startRecursivelyDepthVisit(5, printer);
        System.out.println();
        System.out.println();

        System.out.println("Обход по графу в ширину:");
        System.out.println("Первая компонента:");
        graph.startWidthVisit(0, printer);
        System.out.println();
        System.out.println("Вторая компонента:");
        graph.startWidthVisit(4, printer);
        System.out.println();
        System.out.println("Третья компонента:");
        graph.startWidthVisit(5, printer);
        System.out.println();
        System.out.println();
    }
}