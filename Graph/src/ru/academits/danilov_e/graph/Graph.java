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

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }

            queue.add(i);

            while (!queue.isEmpty()) {
                Integer vertex = queue.remove();
                visited[vertex] = true;

                consumer.accept(vertex);

                for (int j = 0; j < connectivityMatrix.length; j++) {
                    if (connectivityMatrix[vertex][j] != 0 && !queue.contains(j) && !visited[j]) {
                        queue.add(j);
                    }
                }
            }
        }
    }

    public void visitInDepth(IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }

            deque.add(i);

            while (!deque.isEmpty()) {
                Integer vertex = deque.removeFirst();
                visited[vertex] = true;

                consumer.accept(vertex);

                for (int j = connectivityMatrix.length - 1; j >= 0; j--) {
                    if (connectivityMatrix[vertex][j] != 0 && !deque.contains(j) && !visited[j]) {
                        deque.addFirst(j);
                    }
                }
            }
        }
    }

    public void visitInDepthRecursively(IntConsumer consumer) {
        boolean[] visited = new boolean[connectivityMatrix.length];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visitInDepthRecursively(i, visited, consumer);
            }
        }
    }

    private void visitInDepthRecursively(int vertex, boolean[] visited, IntConsumer consumer) {
        consumer.accept(vertex);
        visited[vertex] = true;

        for (int i = 0; i < connectivityMatrix.length; i++) {
            if (connectivityMatrix[vertex][i] != 0 && !visited[i]) {
                visitInDepthRecursively(i, visited, consumer);
            }
        }
    }
}