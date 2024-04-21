package ru.academits.danilov_e.graph_main;

import ru.academits.danilov_e.graph.Graph;

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

        System.out.println("Массив посещенных вершин:");
        graph.showVisitedNodes();
        System.out.println();

        System.out.println("Проход по графу в глубину:");
        graph.startVisit("Deep");
        System.out.println();

        System.out.println("Массив посещенных вершин:");
        graph.showVisitedNodes();
        System.out.println();

        graph.clearVisitedNodes();

        System.out.println("Массив посещенных вершин:");
        graph.showVisitedNodes();
        System.out.println();

        System.out.println("Проход по графу в ширину:");
        graph.startVisit("Width");
        System.out.println();

        System.out.println("Массив посещенных вершин:");
        graph.showVisitedNodes();
    }
}