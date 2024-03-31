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

        graph.showVisitedNodes();
    }
}