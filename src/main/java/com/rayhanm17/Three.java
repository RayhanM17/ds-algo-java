package com.rayhanm17;

import java.io.*;
import java.util.*;

public class Three{

    public static void findCourseOrder(String fileName) throws IOException {
        // Parse the input file and build the graph
        HashMap<Vertex, Set<Vertex>> graph = new HashMap<>();
        Map<String, Vertex> vertexMap = new HashMap<>();

        // Read the file line by line
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String courseName = parts[0];
                Vertex courseVertex = vertexMap.computeIfAbsent(courseName, Vertex::new);

                if (parts.length > 1) {
                    String[] prerequisites = parts[1].replace("\"", "").split(",");
                    for (String prereq : prerequisites) {
                        Vertex prereqVertex = vertexMap.computeIfAbsent(prereq, Vertex::new);
                        addEdge(prereqVertex, courseVertex, graph);
                    }
                }
            }
        }

        // Convert the graph keys to an array for processing
        Vertex[] vertices = vertexMap.values().toArray(new Vertex[0]);

        // Perform topological sort
        Vertex.topSortUsingDFS(graph, vertices);

        // Sort by topNum to get the correct order
        Arrays.sort(vertices);

        // Print the course order
        System.out.println("Course order to graduate:");
        for (Vertex vertex : vertices) {
            System.out.println(vertex.name);
        }
    }

    private static void addEdge(Vertex from, Vertex to, HashMap<Vertex, Set<Vertex>> graph) {
        if (!graph.containsKey(from))
            graph.put(from, new HashSet<Vertex>());
        graph.get(from).add(to);
        to.indegree++;
    }
}
