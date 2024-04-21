package ru.academits.danilov_e.graph;

import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    int[][] graph;
    boolean[] hasVisited;

    public Graph(int[][] graph) {
        this.graph = graph;
        hasVisited = new boolean[graph.length];

        Arrays.fill(hasVisited, false);
    }

    public void showVisitedNodes() {
        for (boolean node : hasVisited) {
            System.out.println(node);
        }
    }

    public int checkVisitedNodes() {
        for (int i = 0; i < hasVisited.length; i++) {
            if (!hasVisited[i]) {
                return i;
            }
        }

        return -1;
    }

    public void startVisit(String algorithmType) {
        switch (algorithmType) {
            case "Width":

                while (checkVisitedNodes() != -1) {
                    visitInWidth(checkVisitedNodes());
                }

                break;
            case "Deep":

                while (checkVisitedNodes() != -1) {
                    visitInDepth(checkVisitedNodes());
                }

                break;
            default:
                throw new IllegalArgumentException("Выбран не верный вид обхода графа. Возможные варианты Width " +
                        "или Deep. Ваш вариант: " + algorithmType);
        }
    }

    private LinkedList<Integer> getNearestNodesList(int nodes) {
        LinkedList<Integer> nodesList = new LinkedList<>();

        int[] nodesLine = graph[nodes];

        for (int i = 0; i < nodesLine.length; i++) {
            if (nodesLine[i] != 0 && i != nodes && !hasVisited[i]) {
                nodesList.addLast(i);
            }
        }

        return nodesList;
    }

    private void visitInWidth(int startNode) {
        System.out.println(startNode);
        hasVisited[startNode] = true;
        LinkedList<Integer> nodesList = new LinkedList<>(getNearestNodesList(startNode));

        while (!nodesList.isEmpty()) {
            int node = nodesList.getFirst();
            System.out.println(node);
            hasVisited[node] = true;
            nodesList.removeFirst();
            nodesList.addAll(getNearestNodesList(node));
        }
    }

    private void visitInDepth(int startNode) {
        System.out.println(startNode);
        hasVisited[startNode] = true;
        LinkedList<Integer> temp = getNearestNodesList(startNode);
        temp.reversed();
        LinkedList<Integer> nodesList = new LinkedList<>(temp);

        while (!nodesList.isEmpty()) {
            int node = nodesList.getFirst();
            System.out.println(node);
            hasVisited[node] = true;
            nodesList.removeFirst();
            nodesList.addAll(getNearestNodesList(node).reversed());
        }
    }

    public void clearVisitedNodes() {
        Arrays.fill(hasVisited, false);
    }
}
