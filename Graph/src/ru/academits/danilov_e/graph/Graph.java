package ru.academits.danilov_e.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
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

        Stack<Integer> list = new Stack<>();

        list.addFirst(startVertex);

        while (!list.isEmpty()) {
            Integer vertex = list.removeFirst();
            visited[vertex] = true;

            consumer.accept(vertex);

            for (int i = 0; i < connectivityMatrix.length; i++) {
                if (connectivityMatrix[vertex][i] != 0 && !visited[i]) {
                    list.add(i);
                }
            }
        }
    }

    public void startRecursivelyDepthVisit(int startVertex, IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];
        Stack<Integer> list = new Stack<>();
        list.add(startVertex);
        startRecursivelyDepthVisit(list, visited, consumer);
    }

    private void startRecursivelyDepthVisit(Stack<Integer> list, boolean[] array, IntConsumer consumer) {
        while (!list.isEmpty()) {
            int vertex = list.removeFirst();
            consumer.accept(vertex);
            array[vertex] = true;

            for (int i = 0; i < connectivityMatrix.length; i++) {
                if (connectivityMatrix[vertex][i] != 0 && !array[i]) {
                    list.add(i);
                }
            }

            startRecursivelyDepthVisit(list, array, consumer);
        }
    }
}