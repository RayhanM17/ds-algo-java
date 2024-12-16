package com.rayhanm17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class IslandBridgeBuilder {

    // Edge class to represent a connection between two islands
    public static class Edge {
        int island1;
        int island2;
        double distance;

        public Edge(int island1, int island2, double distance) {
            this.island1 = island1;
            this.island2 = island2;
            this.distance = distance;
        }
    }

    // Union-Find (Disjoint Set) class to support Kruskal's algorithm
    public static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false; // Already connected
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    // Method to read the CSV file and return the island coordinates
    public static HashMap<Integer, double[]> readCSV(String filePath) throws Exception {
        HashMap<Integer, double[]> islands = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine(); // Skip header
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int island = Integer.parseInt(parts[0]);
            double latitude = Double.parseDouble(parts[1]);
            double longitude = Double.parseDouble(parts[2]);
            islands.put(island, new double[]{latitude, -longitude}); // Convert longitude to negative for western hemisphere
        }
        br.close();
        return islands;
    }

    // Method to calculate direct distance between two coordinates
    public static double getDirectDistance(double lat1, double lon1, double lat2, double lon2) {
        double psi1 = lat1 * Math.PI / 180;
        double psi2 = lat2 * Math.PI / 180;
        double deltaPsi = psi2 - psi1;
        double deltaLambda = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(deltaPsi / 2) * Math.sin(deltaPsi / 2) +
                   Math.cos(psi1) * Math.cos(psi2) *
                   Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        return 2 * 6371e3 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    // Kruskal's algorithm to find MST and return the total distance
    public static double kruskalsAlgorithm(List<Edge> edges, int numIslands) {
        Collections.sort(edges, Comparator.comparingDouble(e -> e.distance));
        UnionFind uf = new UnionFind(numIslands);
        double totalDistance = 0;

        for (Edge edge : edges) {
            if (uf.union(edge.island1, edge.island2)) {
                totalDistance += edge.distance;
            }
        }
        return totalDistance;
    }

    // Method to calculate the minimum bridge length
    public static double calculateMinimumBridgeLength(String filePath) throws Exception {
        // Step 1: Read the CSV file and parse island data
        HashMap<Integer, double[]> islands = readCSV(filePath);

        // Step 2: Generate all possible edges with distances
        List<Edge> edges = new ArrayList<>();
        int numIslands = islands.size();
        for (int i = 0; i < numIslands; i++) {
            for (int j = i + 1; j < numIslands; j++) {
                double[] coord1 = islands.get(i);
                double[] coord2 = islands.get(j);
                double distance = getDirectDistance(coord1[0], coord1[1], coord2[0], coord2[1]);
                edges.add(new Edge(i, j, distance));
            }
        }

        // Step 3: Run Kruskal's algorithm to find MST
        return kruskalsAlgorithm(edges, numIslands);
    }
}