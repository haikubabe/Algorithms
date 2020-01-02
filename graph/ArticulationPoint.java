package graph;

import java.util.Arrays;
import java.util.Stack;

/**
 * find the articulation points, bridges and
 * check if a graph is biconnected or not
 */
public class ArticulationPoint {

    private static int dfsRoot;
    private static int rootChildren;
    private static int count;
    private static boolean[] explored;
    private static int[] dfs_parent;
    private static int[] dfs_num;
    private static int[] dfs_low;
    private static boolean[] articulation_vertex;

    private static void initialize(Graph graph) {
        count = 0;
        explored = new boolean[graph.getVertices()];
        dfs_parent = new int[graph.getVertices()];
        Arrays.fill(dfs_parent, -1);
        dfs_num = new int[graph.getVertices()];
        dfs_low = new int[graph.getVertices()];
        articulation_vertex = new boolean[graph.getVertices()];
    }

    static class Edge {
        int u;
        int v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    /**
     * compute articulation points and bridges
     *
     * @param graph
     */
    private static void articulationPointAndBridge(Graph graph) {
        initialize(graph);
        for (int i = 0; i < graph.getVertices(); i++) {
            if (!explored[i]) {
                dfsRoot = i;
                rootChildren = 0;
                findArticulationPointAndBridge(graph, i);
                //if the root has more than 1 children/neighbors then it is an articulation point
                articulation_vertex[i] = (rootChildren > 1);
            }
        }

        System.out.println("Articulation Points : ");
        for (int i = 0; i < graph.getVertices(); i++) {
            if (articulation_vertex[i]) {
                System.out.printf("Vertex %d\n", i);
            }
        }
    }

    /**
     * find articulation points and bridges
     *
     * @param graph
     * @param u
     */
    private static void findArticulationPointAndBridge(Graph graph, int u) {
        dfs_low[u] = dfs_num[u] = count++;  //dfs_low[u] <= dfs_num[u]
        explored[u] = true;
        for (int v : graph.getAdj()[u]) {
            if (!explored[v]) {
                dfs_parent[v] = u;
                //special case
                if (dfsRoot == u) {
                    rootChildren++;
                }
                findArticulationPointAndBridge(graph, v);
                if (dfs_low[v] >= dfs_num[u]) {
                    articulation_vertex[u] = true;
                }
                if (dfs_low[v] > dfs_num[u]) {
                    System.out.printf("Edge (%d, %d) is a bridge\n", u, v);
                }
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
            }
            //for back edge i.e. if v is not a direct parent of u
            else if (dfs_parent[u] != v) {
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
            }
        }
    }

    /**
     * compute the biconnected components of a graph
     * @param graph
     */
    private static void computeBiconnectedComponent(Graph graph) {
        initialize(graph);
        Stack<Edge> stack = new Stack<>();
        System.out.println("Biconnected Components : ");
        for (int i = 0; i < graph.getVertices(); i++) {
            if (!explored[i]) {
                dfsRoot = i; rootChildren = 0;
                findBiconnectedComponent(graph, i, stack);
            }
        }
    }

    /**
     * find the biconnected components of a graph
     * @param graph
     * @param u
     * @param stack
     */
    private static void findBiconnectedComponent(Graph graph, int u, Stack<Edge> stack) {
        dfs_low[u] = dfs_num[u] = count++;
        explored[u] = true;
        for (int v : graph.getAdj()[u]) {
            if (!explored[v]) {
                dfs_parent[v] = u;
                stack.push(new Edge(u, v));
                if (dfsRoot == u) {
                    rootChildren++;
                }
                findBiconnectedComponent(graph, v, stack);
                //when you find an articulation point
                if (dfs_low[v] >= dfs_num[u] || rootChildren > 1) {
                    while (!stack.isEmpty() && stack.peek().u != u && stack.peek().v != v) {
                        Edge edge = stack.pop();
                        System.out.print(edge.u + " - " + edge.v + " ");
                    }
                    if  (!stack.isEmpty()) {
                        Edge edge = stack.pop();
                        System.out.println(edge.u + " - " + edge.v + " ");
                    }
                }
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
            }
            else if (dfs_parent[u] != v && dfs_num[u] > dfs_num[v]) {
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
                stack.push(new Edge(u, v));
            }
        }
    }

    /**
     * check if the graph is biconnected or not
     *
     * @param graph
     * @return
     */
    private static boolean isBiconnectedGraph(Graph graph) {
        if (checkConnected(graph)) {
            return checkNoArticulationPoint(graph);
        }
        return false;
    }

    /**
     * check if the graph is connected or not
     *
     * @param graph
     * @return
     */
    private static boolean checkConnected(Graph graph) {
        explored = new boolean[graph.getVertices()];
        for (int i = 0; i < graph.getVertices(); i++) {
            if (!explored[i]) {
                DFS(graph, i);
            }
        }
        for (int i = 0; i < graph.getVertices(); i++) {
            if (!explored[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * compute dfs
     *
     * @param graph
     * @param u
     */
    private static void DFS(Graph graph, int u) {
        explored[u] = true;
        for (int v : graph.getAdj()[u]) {
            if (!explored[v]) {
                DFS(graph, v);
            }
        }
    }

    /**
     * check if the graph has articulation points or not
     *
     * @param graph
     * @return
     */
    private static boolean checkNoArticulationPoint(Graph graph) {
        initialize(graph);
        for (int i = 0; i < graph.getVertices(); i++) {
            if (!explored[i]) {
                dfsRoot = i;
                rootChildren = 0;
                findArticulationPointAndBridge(graph, i);
                //if the root has more than 1 children/neighbors then it is an articulation point
                articulation_vertex[i] = (rootChildren > 1);
            }
        }
        for (int i = 0; i < graph.getVertices(); i++) {
            if (articulation_vertex[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);

        articulationPointAndBridge(graph);

        Graph graph1 = new Graph(6);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 4);
        graph1.addEdge(3, 4);
        graph1.addEdge(4, 5);

        articulationPointAndBridge(graph1);

        Graph graph2 = new Graph(5);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 3);
        graph2.addEdge(1, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(2, 4);
        graph2.addEdge(3, 4);

        if (isBiconnectedGraph(graph2)) {
            System.out.println("Graph is biconnected");
        } else {
            System.out.println("Graph is not biconnected");
        }

        computeBiconnectedComponent(graph);
    }
}
