package com.rayhanm17;

import java.util.HashMap;
import java.util.List;

public class Two {

    public static boolean canTravel(HashMap<String, List<String>> bridges, String source, String destination) {
        // Create a DisjointSet for the islands
        DisjointSet<String> disjointSet = new DisjointSet<>();

        // Add all bridges to the DisjointSet
        for (String island : bridges.keySet()) {
            disjointSet.find(island); // Ensures each island is part of the DisjointSet
            for (String connectedIsland : bridges.get(island)) {
                disjointSet.find(connectedIsland); // Ensures connected islands are also part of the DisjointSet
                disjointSet.union(island, connectedIsland); // Connect the islands via union
            }
        }

        // Check if source and destination are in the same set
        return disjointSet.find(source).equals(disjointSet.find(destination));
    }
}

