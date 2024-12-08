package com.rayhanm17;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) {
        int[] list1 = {3, 5, 8, 9};
        int[] list2 = {2, 6, 10, 12};

        System.out.println(One.findMedian(list1, list2));

        // Example usage
        HashMap<String, List<String>> bridges = new HashMap<>();
        bridges.put("A", List.of("B", "C"));
        bridges.put("B", List.of("A", "D"));
        bridges.put("C", List.of("A"));
        bridges.put("D", List.of("B"));

        System.out.println(Two.canTravel(bridges, "A", "D")); // true
        System.out.println(Two.canTravel(bridges, "A", "E")); // false

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        try {
            Three.findCourseOrder("./src/main/java/com/rayhanm17/prerequisites.txt");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Example courses and prerequisites
        HashMap<Vertex, Set<Vertex>> graph = new HashMap<>();
        Map<String, Vertex> vertexMap = new HashMap<>();

        // Sample vertices (courses)
        Vertex v1 = new Vertex("COP2210");
        Vertex v2 = new Vertex("COP3337");
        Vertex v3 = new Vertex("COT3100");
        Vertex v4 = new Vertex("COP3530");
        Vertex v5 = new Vertex("COP4338");
        Vertex v6 = new Vertex("COP4226");
        Vertex v7 = new Vertex("COP4610");
        Vertex v8 = new Vertex("CDA3102");

        vertexMap.put(v1.name, v1);
        vertexMap.put(v2.name, v2);
        vertexMap.put(v3.name, v3);
        vertexMap.put(v4.name, v4);
        vertexMap.put(v5.name, v5);
        vertexMap.put(v6.name, v6);
        vertexMap.put(v7.name, v7);
        vertexMap.put(v8.name, v8);

        // Add edges representing prerequisites (from -> to)
        // For simplicity, we'll add edges in both directions manually here
        addEdge(v1, v2, graph); // COP2210 -> COP3337
        addEdge(v2, v4, graph); // COP3337 -> COP3530
        addEdge(v3, v1, graph); // COT3100 -> COP2210
        addEdge(v4, v5, graph); // COP3530 -> COP4338
        addEdge(v5, v6, graph); // COP4338 -> COP4226
        addEdge(v4, v7, graph); // COP3530 -> COP4610
        addEdge(v7, v8, graph); // COP4610 -> CDA3102

        // Create an array of courses for easy processing
        Vertex[] vertices = new Vertex[] { v1, v2, v3, v4, v5, v6, v7, v8 };

        // Perform BFS traversal and get the order
        Four.getOrderAndPrintBFS(graph, vertices);
    }

    private static void addEdge(Vertex from, Vertex to, HashMap<Vertex, Set<Vertex>> graph) {
        if (!graph.containsKey(from))
            graph.put(from, new HashSet<Vertex>());
        graph.get(from).add(to);
        to.indegree++;
    }
}