package graph;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Edge weighted digraph
 */
public class WeightedGraph {

    private int vertices;
    private LinkedList<Pair<Integer, Integer>>[] adj;
    private boolean[] visited;
    private int[] dist;

    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        this.adj = new LinkedList[this.vertices];
        for (int i = 0; i < this.vertices; i++) {
            adj[i] = new LinkedList<>();
        }
        this.visited = new boolean[this.vertices];
        this.dist = new int[this.vertices];
    }

    private void addDirectedEdge(int source, int dest, int wt) {
        this.adj[source].add(new Pair<>(dest, wt));
    }

    private int minDist() {
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

    private int[] initialize(int s) {
        dist[s] = 0;
        for (int i = 0; i < vertices; i++) {
            if (i != s) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        return dist;
    }

    private void relax(int u, int v, int w) {
        if (dist[v] > dist[u] + w) {
            dist[v] = dist[u] + w;
        }
    }

    /**
     * compute single source shortest path using dijkstra
     * @param s
     * @return
     */
    private String dijkstra(int s) {
        initialize(s);
        StringBuilder str = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(vertices);
        for (int i = 0; i < vertices; i++) {
            pq.add(i);
        }
        while (!pq.isEmpty()) {
            int u = minDist();
            pq.remove(u);
            str.append(u);
            visited[u] = true;
            for (Pair<Integer, Integer> p : adj[u]) {
                int v = p.getKey();
                int wt = p.getValue();
                relax(u, v, wt);
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

        /*WeightedGraph graph1 = new WeightedGraph(5);
        graph1.addDirectedEdge(1, 0, 1);
        graph1.addDirectedEdge(1, 2, 7);
        graph1.addDirectedEdge(1, 4, 2);
        graph1.addDirectedEdge(0, 2, 3);
        graph1.addDirectedEdge(2, 3, 1);
        graph1.addDirectedEdge(2, 4, 5);
        graph1.addDirectedEdge(3, 4, 7);
        System.out.println(graph1.dijkstra(1));

        WeightedGraph graph2 = new WeightedGraph(5);
        graph2.addDirectedEdge(0, 1, 10);
        graph2.addDirectedEdge(0, 3, 5);
        graph2.addDirectedEdge(1, 3, 2);
        graph2.addDirectedEdge(1, 2, 1);
        graph2.addDirectedEdge(3, 4, 2);
        graph2.addDirectedEdge(3, 1, 3);
        graph2.addDirectedEdge(3, 2, 9);
        graph2.addDirectedEdge(4, 2, 6);
        graph2.addDirectedEdge(4, 0, 7);
        graph2.addDirectedEdge(2, 4, 4);
        System.out.println(graph2.dijkstra(0));*/
    }
}
