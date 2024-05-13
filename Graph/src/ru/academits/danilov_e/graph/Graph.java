package ru.academits.danilov_e.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] connectivityMatrix;

    public Graph(int[][] connectivityMatrix) {
        for (int[] row : connectivityMatrix) {
            if (connectivityMatrix.length != row.length) {
                throw new IllegalArgumentException("Connectivity matrix must have same count of rows and columns");
            }
        }

        this.connectivityMatrix = connectivityMatrix;
    }

    public void startWidthVisit(int startVertex, IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Integer vertex = queue.remove();
            visited[vertex] = true;

            consumer.accept(vertex);

            for (int i = 0; i < connectivityMatrix.length; i++) {
                if (connectivityMatrix[vertex][i] != 0 && !visited[i]) {
                    queue.add(i);
                }
            }
        }
    }

    public void startDepthVisit(int startVertex, IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        Deque<Integer> deque = new LinkedList<>();

        deque.add(startVertex);

        while (!deque.isEmpty()) {
            Integer vertex = deque.removeFirst();
            visited[vertex] = true;

            consumer.accept(vertex);

            for (int i = connectivityMatrix.length - 1; i >= 0; i--) {
                if (connectivityMatrix[vertex][i] != 0 && !visited[i]) {
                    deque.addFirst(i);
                }
            }
        }
    }

    public void startRecursivelyDepthVisit(int startVertex, IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];
        startRecursivelyDepthVisit(startVertex, visited, consumer);
    }

    private void startRecursivelyDepthVisit(int vertex, boolean[] array, IntConsumer consumer) {
        consumer.accept(vertex);
        array[vertex] = true;

        for (int i = 0; i < connectivityMatrix.length; i++) {
            if (connectivityMatrix[vertex][i] != 0 && !array[i]) {
                startRecursivelyDepthVisit(i, array, consumer);
            }
        }
    }
}