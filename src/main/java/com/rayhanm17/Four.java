package com.rayhanm17;

public static void bfsTraversal(HashMap<Vertex, Set<Vertex>> graph, Vertex[] vertices) {
    // Initialize the queue for BFS
    Queue<Vertex> queue = new LinkedList<>();
    
    // Add all vertices with indegree of 0 to the queue (i.e., no prerequisites)
    for (Vertex v : vertices) {
        if (v.indegree == 0) {
            queue.add(v);
        }
    }

    // Process the graph in BFS order
    while (!queue.isEmpty()) {
        // Dequeue the current vertex
        Vertex current = queue.poll();
        
        // Print the current course
        System.out.println("Course: " + current.name);

        // For each neighbor, reduce the indegree and add to the queue if indegree becomes 0
        if (graph.containsKey(current)) {
            for (Vertex neighbor : graph.get(current)) {
                neighbor.indegree--;
                if (neighbor.indegree == 0) {
                    queue.add(neighbor);
                }
            }
        }
    }
}
