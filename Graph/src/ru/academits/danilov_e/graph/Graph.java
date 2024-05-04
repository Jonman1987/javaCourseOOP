package ru.academits.danilov_e.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public class Graph {
    private final int[][] connectivityMatrix;

    public Graph(int[][] connectivityMatrix) {
        if (connectivityMatrix.length != connectivityMatrix[0].length) {
            throw new IllegalArgumentException("Connectivity matrix must have same count of rows and columns");
        }

        this.connectivityMatrix = connectivityMatrix;
    }

    public void widthVisit(int startVertex, Consumer<Integer> consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(startVertex);
        Integer vertex;

        while (!queue.isEmpty()) {
            vertex = queue.remove();
            visited[vertex] = true;

            consumer.accept(vertex);

            for (int i = 0; i < connectivityMatrix.length; i++) {
                if (connectivityMatrix[vertex][i] != 0 && !visited[i]) {
                    queue.add(i);
                }
            }
        }
    }

    public void depthVisit(int startVertex, Consumer<Integer> consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        Stack<Integer> list = new Stack<>();

        list.addFirst(startVertex);
        Integer vertex;

        while (!list.isEmpty()) {
            vertex = list.removeFirst();
            visited[vertex] = true;

            consumer.accept(vertex);

            for (int i = 0; i < connectivityMatrix.length; i++) {
                if (connectivityMatrix[vertex][i] != 0 && !visited[i]) {
                    list.add(i);
                }
            }
        }
    }

    public <T> void recursivelyDepthVisit(int startVertex, Consumer<Integer> consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];
        Stack<Integer> list = new Stack<>();
        list.add(startVertex);
        depthVisit(list, visited, consumer);
    }

    private void depthVisit(Stack<Integer> list, boolean[] array, Consumer<Integer> consumer) {
        while (!list.isEmpty()) {
            int vertex = list.removeFirst();
            consumer.accept(vertex);
            array[vertex] = true;

            for (int i = 0; i < connectivityMatrix.length; i++) {
                if (connectivityMatrix[vertex][i] != 0 && !array[i]) {
                    list.add(i);
                }
            }

            depthVisit(list, array, consumer);
        }
    }

    public void recursivelyWidthVisit(int startVertex, Consumer<Integer> consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        widthVisit(queue, visited, consumer);
    }

    private void widthVisit(Queue<Integer> queue, boolean[] array, Consumer<Integer> consumer) {
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            array[vertex] = true;

            consumer.accept(vertex);

            for (int i = 0; i < connectivityMatrix.length; i++) {
                if (connectivityMatrix[vertex][i] != 0 && !array[i]) {
                    queue.add(i);
                }
            }

            widthVisit(queue, array, consumer);
        }
    }
}
