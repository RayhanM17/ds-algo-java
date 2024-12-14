package com.rayhanm17;

import java.util.*;


public class App{
    public static class Vertex implements Comparable<Vertex>{
        public String name;
        public int indegree;
        public int topNum;
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
            return name;
        }
        public Vertex(String name) {
            this.name = name;
            this.indegree = 0;
            this.topNum = -1;
        }
        @Override
        public int compareTo(Vertex other){
            return this.topNum - other.topNum;
        }
    }
    private static void addEdge(Vertex from, Vertex to, Map<Vertex, Set<Vertex>> graph) {
        //Adding edge from--to
        if (!graph.containsKey(from))
            graph.put(from, new HashSet<Vertex>());
        if (!graph.containsKey(to))
            graph.put(to, new HashSet<Vertex>());
        graph.get(from).add(to);
        to.indegree++;
    }
    public static void kahns_topsort(Map<Vertex, Set<Vertex>> dag) throws Exception {
        int counter = 0;
        Queue<Vertex> queue = new LinkedList<>();
        for (Vertex v : dag.keySet())
            if (v.indegree == 0)
                queue.add(v);
        while (!queue.isEmpty()) {
            Vertex v = queue.remove();
            v.topNum = counter++;
            for (Vertex neighbor : dag.get(v)) {
                neighbor.indegree--;
                if (neighbor.indegree == 0)
                    queue.add(neighbor);
            }
        }
        for(Vertex v: dag.keySet())
            if(v.topNum == -1)
                throw new Exception("Given graph contains a cycle!");
        List<Vertex> allVertices = new ArrayList();
        for(Vertex v: dag.keySet())
            allVertices.add(v);
        Collections.sort(allVertices);
        System.out.println("Topological sort: " + allVertices);

    }
    public static void addEdges(Vertex[] vertex, String[] edges,
                                Map<Vertex,Set<Vertex>> graph, boolean directed){
        for(String edge: edges){
            int first = Integer.parseInt(edge.substring(0, edge.indexOf(',')));
            int second = Integer.parseInt(edge.substring(edge.indexOf(',')+1));
            addEdge(vertex[first], vertex[second], graph);
            if(!directed)
                addEdge(vertex[second], vertex[first], graph);
        }
    }
    public static void main(String[] args) {
        Map<Vertex, Set<Vertex>> dag = new HashMap<>();
        Vertex[] vertex = new Vertex[5];
        for(int i = 0; i < 5;i++)
            vertex[i] = new Vertex(i + "");
        String[] edges = new String[]{
        "0,1", "0,2", "0,3", "0,4", 
        "1,3", "2,3", "2,4", "3,4"
        };
        addEdges(vertex, edges, dag, true);//adding all edges
        try{
            kahns_topsort(dag);
        }catch(Exception exp){
            System.out.println("Error: Loop detected in DAG!");
            System.exit(1);
        }
   }
}