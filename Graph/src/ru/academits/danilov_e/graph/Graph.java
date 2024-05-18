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

    public void visitInWidth(IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        Queue<Integer> queue = new LinkedList<>();

        int startVertex = 0;

        while (true) {
            boolean hasStop = false;

            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    startVertex = i;
                    hasStop = false;
                    break;
                }

                hasStop = true;
            }

            if (hasStop) {
                return;
            }

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
    }

    public void visitInDepth(IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        Deque<Integer> deque = new LinkedList<>();

        int startVertex = 0;

        while (true) {
            boolean hasStop = false;

            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    startVertex = i;
                    hasStop = false;
                    break;
                }

                hasStop = true;
            }

            if (hasStop) {
                return;
            }

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
    }

    public void visitInDepthRecursively(IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        int startVertex = 0;

        while (true) {
            boolean hasStop = false;

            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    startVertex = i;
                    hasStop = false;
                    break;
                }

                hasStop = true;
            }

            if (hasStop) {
                return;
            }

            visitInDepthRecursively(startVertex, visited, consumer);
        }
    }

    private void visitInDepthRecursively(int vertex, boolean[] visitedVertexesArray, IntConsumer consumer) {
        consumer.accept(vertex);
        visitedVertexesArray[vertex] = true;

        for (int i = 0; i < connectivityMatrix.length; i++) {
            if (connectivityMatrix[vertex][i] != 0 && !visitedVertexesArray[i]) {
                visitInDepthRecursively(i, visitedVertexesArray, consumer);
            }
        }
    }
}