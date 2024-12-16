package com.rayhanm17;

import java.util.HashMap;
import java.util.HashSet;

public class App {

    public static void main(String[] args) {
        // Create a simple binary tree
        BinaryNode<Vertex> root = new BinaryNode<>(new Vertex("A"));
        root.left = new BinaryNode<>(new Vertex("B"));
        root.right = new BinaryNode<>(new Vertex("C"));
        root.left.left = new BinaryNode<>(new Vertex("D"));
        root.left.right = new BinaryNode<>(new Vertex("E"));
        root.right.left = new BinaryNode<>(new Vertex("F"));

        // Convert the tree to a graph
        HashMap<Vertex, HashSet<Vertex>> graph = treeToGraph(root);

        // Print the graph
        for (Vertex v : graph.keySet()) {
            System.out.println(v + " -> " + graph.get(v));
        }

        try {
            // Path to the CSV file
            String filePath = "./src/main/java/com/rayhanm17/islands.csv";

            // Calculate the minimum total length of bridges required
            double minimumBridgeLength = IslandBridgeBuilder.calculateMinimumBridgeLength(filePath);

            // Print the result
            System.out.printf("The minimum total length of bridges required: %.2f meters%n", minimumBridgeLength);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Question 1

    public static HashMap<Vertex, HashSet<Vertex>> treeToGraph(BinaryNode<Vertex> root) {
        HashMap<Vertex, HashSet<Vertex>> graph = new HashMap<>();

        treeToGraphHelper(root, graph);

        return graph;
    }

    private static void treeToGraphHelper(BinaryNode<Vertex> node, HashMap<Vertex, HashSet<Vertex>> graph) {
        if (node == null) return;

        graph.putIfAbsent(node.data, new HashSet<>());

        if (node.left != null) {
            graph.get(node.data).add(node.left.data);
            graph.putIfAbsent(node.left.data, new HashSet<>());
            treeToGraphHelper(node.left, graph);
        }

        if (node.right != null) {
            graph.get(node.data).add(node.right.data);
            graph.putIfAbsent(node.right.data, new HashSet<>());
            treeToGraphHelper(node.right, graph);
        }
    }
}