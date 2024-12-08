package com.rayhanm17;

import java.util.*;

public class Vertex implements Comparable<Vertex> {
    public String name;
    public int indegree;
    public int topNum;
    public int discovered;
    public int finished;
    public boolean processed;
    public Vertex pred;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        Vertex another = (Vertex) o;
        return this.name.equals(another.name);
    }

    @Override
    public String toString() {
        return name + " w/ topNum/ discovered/ finished: " + topNum + "/ " + discovered + "/ " + finished;
    }

    public Vertex(String name) {
        this.name = name;
        this.indegree = 0;
        this.discovered = 0;
        this.processed = false;
    }

    public int compareTo(Vertex another) {
        return topNum - another.topNum;
    }

    public static void topSortUsingDFS(HashMap<Vertex, Set<Vertex>> graph, Vertex[] vertices){
        LinkedList<Vertex> starters = new LinkedList<Vertex>();
        for (Vertex v : vertices)
            if (v.indegree == 0)
                starters.add(v);
        Stack<Vertex> stack = new Stack<Vertex>();
        int time = 0;
        int topNum = vertices.length; 
        for (Vertex startVertex : starters) {
            stack.push(startVertex);
            while (!stack.empty()) {
                Vertex current = stack.pop();
                if (current.finished > 0)
                    continue;
                if (current.processed) {
                    current.finished = ++time;
                    current.topNum = topNum--;
                    continue;
                }
                current.discovered = ++time;
                if (!graph.containsKey(current)) {// out-degree = 0
                    current.finished = ++time;
                    current.topNum = topNum--;
                    continue;
                }
                current.processed = true;
                stack.push(current);
                for (Vertex neighbor : graph.get(current))
                    if (neighbor.discovered == 0) {
                        neighbor.pred = current;
                        stack.push(neighbor);
                    }
            }
        }
    }
}