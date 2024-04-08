package ru.academits.danilov_e.graph;

import java.util.Arrays;

public class Graph {
    int[][] graph;
    boolean[] hasVisited;

    public Graph(int[][] graph){
        this.graph = graph;
        hasVisited = new boolean[graph.length];

        Arrays.fill(hasVisited, false);
    }

    public void showVisitedNodes(){
        for(boolean node : hasVisited){
            System.out.println(node);
        }
    }

    public void startHamiltonCycle(){
        for(int i = 0; i < hasVisited.length; i++){
            if(!hasVisited[i]){
                hamiltonCycle(i);
            }
        }
    }

    private void hamiltonCycle(int node){

    }

}
