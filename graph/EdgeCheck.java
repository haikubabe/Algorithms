package graph;

import java.util.Arrays;

/**
 * check the graph edge property via DFS
 * spanning tree
 */
public class EdgeCheck {

    enum State {
        DFS_WHITE,  //unexplored
        DFS_GRAY,   //explored but not yet completed
        DFS_BLACK   //explored and completed
    }

    private static State[] explored;
    private static int[] parent;

    private static void initialize(Graph graph) {
        explored = new State[graph.getVertices()];
        Arrays.fill(explored, State.DFS_WHITE);
        parent = new int[graph.getVertices()];
        Arrays.fill(parent, -1);
    }

    private static void computeGraphCheck(Graph graph) {
        initialize(graph);
        for (int i = 0; i < graph.getVertices(); i++) {
            //if unexplored
            if (explored[i] == State.DFS_WHITE) {
                graphCheck(graph, i);
            }
        }
    }

    private static void graphCheck(Graph graph, int u) {
        explored[u] = State.DFS_GRAY;
        for (int v : graph.getAdj()[u]) {
            //gray to white (tree edge)
            if (explored[v] == State.DFS_WHITE) {
                System.out.printf("Tree Edge (%d, %d)\n", u, v);
                parent[v] = u;
                graphCheck(graph, v);
            }
            //gray to gray
            else if (explored[v] == State.DFS_GRAY) {
                //bidirectional edge not a cycle
                if (parent[u] == v) {
                    System.out.printf("Bidirectional (%d, %d) - (%d, %d)\n", u, v, v, u);
                }
                //back edge cycle
                else {
                    System.out.printf("Back Edge (%d, %d) (Cycle)\n", u, v);
                }
            }
            //gray to black (forward/cross edge)
            else if (explored[v] == State.DFS_BLACK) {
                System.out.printf("Forward/Cross Edge (%d, %d)\n", u, v);
            }
        }
        explored[u] = State.DFS_BLACK;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(6, 7);
        graph.addEdge(5, -1);

        computeGraphCheck(graph);
    }
}
