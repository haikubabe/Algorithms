package graph;

import java.util.Arrays;

/**
 * fill the connected components of an undirected graph
 * one of the application of dfs
 */
public class FloodFill {

    private static int[] color;

    private static void initialize(Graph graph) {
        color = new int[graph.getVertices()];
        Arrays.fill(color, -1);
    }

    private static void dfs(Graph graph) {
        initialize(graph);
        int c = 0;
        for (int i = 0; i < graph.getVertices(); i++) {
            //not yet explored
            if (color[i] == -1) {
                System.out.print("Vertex ");
                floodFill(graph, i, ++c);
                System.out.printf("have color %d\n", color[i]);
            }
        }
    }

    /**
     * color the vertices of the graph
     * @param graph
     * @param u
     * @param c
     */
    private static void floodFill(Graph graph, int u, int c) {
        color[u] = c;
        System.out.print(u + " ");
        for (int v : graph.getAdj()[u]) {
            if (color[v] == -1) {
                floodFill(graph, v, c);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(3,2);
        graph.addEdge(3,4);
        graph.addEdge(6,7);
        graph.addEdge(5,-1);

        dfs(graph);
    }
}
