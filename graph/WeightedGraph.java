package graph;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class WeightedGraph {

    private int vertices;
    private LinkedList<Pair<Integer, Integer>>[] adj;
    private boolean[] visited;

    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        this.adj = new LinkedList[this.vertices];
        for (int i = 0; i < this.vertices; i++) {
            adj[i] = new LinkedList<>();
        }
        this.visited = new boolean[this.vertices];
    }

    private void addDirectedEdge(int source, int dest, int wt) {
        this.adj[source].add(new Pair<>(dest, wt));
    }

    private int minDist(int[] dist) {
        int minDist = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] < minDist && !this.visited[i]) {
                minDist = dist[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    /**
     * compute single source shortest path using dijkstra
     * @param s
     * @return
     */
    private String dijkstra(int s) {
        int[] dist = new int[vertices];
        dist[s] = 0;
        for (int i = 0; i < vertices; i++) {
            if (i != s) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        StringBuilder str = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(vertices);
        for (int i = 0; i < vertices; i++) {
            pq.add(i);
        }
        while (!pq.isEmpty()) {
            int u = minDist(dist);
            pq.remove(u);
            str.append(u);
            visited[u] = true;
            for (Pair<Integer, Integer> p : adj[u]) {
                int v = p.getKey();
                int wt = p.getValue();
                dist[v] = Math.min(dist[v], dist[u] + wt);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);
        graph.addDirectedEdge(1, 0, 2);
        graph.addDirectedEdge(1, 2, 7);
        graph.addDirectedEdge(1, 4, 6);
        graph.addDirectedEdge(0, 2, 3);
        graph.addDirectedEdge(0, 3, 6);
        graph.addDirectedEdge(2, 3, 5);
        graph.addDirectedEdge(4, 3, 1);
        System.out.println(graph.dijkstra(1));

        WeightedGraph graph1 = new WeightedGraph(5);
        graph1.addDirectedEdge(1, 0, 1);
        graph1.addDirectedEdge(1, 2, 7);
        graph1.addDirectedEdge(1, 4, 2);
        graph1.addDirectedEdge(0, 2, 3);
        graph1.addDirectedEdge(2, 3, 1);
        graph1.addDirectedEdge(2, 4, 5);
        graph1.addDirectedEdge(3, 4, 7);
        System.out.println(graph1.dijkstra(1));
    }
}
