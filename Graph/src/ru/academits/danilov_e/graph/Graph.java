package ru.academits.danilov_e.graph;

import java.util.Arrays;

public class Graph<T> {
    T[][] graph;
    boolean[] hasVisited;

    public Graph(T[][] graph){
        this.graph = graph;
        hasVisited = new boolean[graph.length];

        Arrays.fill(hasVisited, false);
    }
}
