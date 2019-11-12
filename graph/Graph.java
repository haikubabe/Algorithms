package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    private int vertices;
    private LinkedList<Integer>[] adj;
    private boolean[] explored;
    private int[] indegree;
    private int finishingTime;

    public Graph(int vertices) {
        this.vertices = vertices;
        adj = new LinkedList[this.vertices];
        for (int i = 0; i < this.vertices; i++) {
            adj[i] = new LinkedList<>();
        }
        explored = new boolean[this.vertices];
        indegree = new int[this.vertices];
        finishingTime = -1;
    }

    private static void addEdge(Graph graph, int source, int dest) {
        graph.adj[source].add(dest);
        graph.adj[dest].add(source);
    }

    private static void addDirectedEdge(Graph graph, int source, int dest) {
        graph.adj[source].add(dest);
        graph.indegree[dest] += 1;
    }

    private static void printGraph(Graph graph) {
        System.out.println("Adjacency List Representation");
        for (int i = 0; i < graph.vertices; i++) {
            System.out.println("adjacency list of vertex " + i);
            System.out.print(i);
            for (int v : graph.adj[i]) {
                System.out.print(" -> " + v);
            }
            System.out.println();
        }
    }

    private static void markUnexplored(Graph graph) {
        for (int i = 0; i < graph.vertices; i++) {
            graph.explored[i] = false;
        }
    }

    /**
     * find the breadth first search
     *
     * @param source
     */
    private static void BFS(Graph graph, int source) {
        graph.explored[source] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            for (int v : graph.adj[u]) {
                if (!graph.explored[v]) {
                    graph.explored[v] = true;
                    queue.offer(v);
                }
            }
        }
        System.out.println();
    }

    /**
     * find the breadth first search recursively
     *
     * @param graph
     */
    private static void BFSR(Graph graph) {
        markUnexplored(graph);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.vertices; i++) {
            if (!graph.explored[i]) {
                graph.explored[i] = true;
                queue.offer(i);
                BFS_Visit(graph, queue);
            }
        }
        System.out.println();
    }

    private static void BFS_Visit(Graph graph, Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }
        int u = queue.poll();
        System.out.print(u + " ");
        for (int v : graph.adj[u]) {
            if (!graph.explored[v]) {
                graph.explored[v] = true;
                queue.offer(v);
            }
        }
        BFS_Visit(graph, queue);
    }

    /**
     * find the shortest path
     *
     * @param s
     * @param v
     */
    private static void findShortestPath(Graph graph, int s, int v) {
        markUnexplored(graph);
        graph.explored[s] = true;
        int[] dist = new int[graph.vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int w : graph.adj[u]) {
                if (!graph.explored[w]) {
                    graph.explored[w] = true;
                    dist[w] = dist[u] + 1;
                    queue.offer(w);
                }
            }
        }
        System.out.println("Shortest distance from s(" + s + ") to v(" + v + ") is " + dist[v]);
    }

    /**
     * find the connected components of a graph
     *
     * @param graph
     */
    private static void findConnectedComponents(Graph graph) {
        markUnexplored(graph);
        for (int i = 0; i < graph.vertices; i++) {
            if (!graph.explored[i]) {
                BFS(graph, i);
            }
        }
    }

    /**
     * find the depth first search
     *
     * @param graph
     * @param source
     */
    private static void DFS(Graph graph, int source) {
        markUnexplored(graph);
        graph.explored[source] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            System.out.print(u + " ");
            for (int v : graph.adj[u]) {
                if (!graph.explored[v]) {
                    graph.explored[v] = true;
                    stack.push(v);
                    break;
                }
            }
        }
        System.out.println();
    }

    /**
     * find the depth first search recursively
     *
     * @param graph
     */
    private static void DFSR(Graph graph) {
        markUnexplored(graph);
        for (int i = 0; i < graph.vertices; i++) {
            if (!graph.explored[i]) {
                DFS_Visit(graph, i);
            }
        }
        System.out.println();
    }

    private static void DFS_Visit(Graph graph, int u) {
        graph.explored[u] = true;
        System.out.print(u + " ");
        for (int v : graph.adj[u]) {
            if (!graph.explored[v]) {
                DFS_Visit(graph, v);
            }
        }
    }

    /**
     * compute the topological sort
     *
     * @param graph
     */
    private static void printTopologicalSort(Graph graph) {
        markUnexplored(graph);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < graph.vertices; i++) {
            if (!graph.explored[i]) {
                findTopologicalSort(graph, i, str);
            }
        }
        System.out.println(str);
    }

    /**
     * find the topological sort using DFS
     * @param graph
     * @param u
     * @param str
     */
    private static void findTopologicalSort(Graph graph, int u, StringBuilder str) {
        graph.explored[u] = true;
        for (int v : graph.adj[u]) {
            if (!graph.explored[v]) {
                findTopologicalSort(graph, v, str);
            }
        }
        str.insert(0, u + " ");
    }

    /**
     * print all the possible topological sort
     * @param graph
     */
    private static void printAllTopologicalSort(Graph graph) {
        markUnexplored(graph);
        StringBuilder str = new StringBuilder();
        findAllTopologicalSort(graph, str);
    }

    /**
     * find all the possible topological sort
     * @param graph
     * @param str
     */
    private static void findAllTopologicalSort(Graph graph, StringBuilder str) {
        if (str.length() == graph.vertices) {
            System.out.println(str.toString());
        }
        for (int i=0;i<graph.vertices;i++) {
            if (!graph.explored[i] && graph.indegree[i] == 0) {
                graph.explored[i] = true;
                str.append(i);
                for (int v : graph.adj[i]) {
                    graph.indegree[v]--;
                }
                findAllTopologicalSort(graph, str);
                for (int v : graph.adj[i]) {
                    graph.indegree[v]++;
                }
                str.deleteCharAt(str.length()-1);
                graph.explored[i] = false;
            }
        }
    }

    /**
     * reverse the graph
     * @param graph
     * @return
     */
    private static Graph reverseGraph(Graph graph) {
        Graph revG = new Graph(graph.vertices);
        for (int i=0;i<graph.vertices;i++) {
            for (int v : graph.adj[i]) {
                addDirectedEdge(revG, v, i);
            }
        }
        return revG;
    }

    /**
     * compute the finishing time of all
     * the vertices of a graph using DFS
     * @param graph
     * @return
     */
    private static int[] firstDFSLoop(Graph graph) {
        int[] f = new int[graph.vertices];
        markUnexplored(graph);
        for (int i=graph.vertices-1;i>=0;i--) {
            if (!graph.explored[i]) {
                findFinishTime(graph, i, f);
            }
        }
        return f;
    }

    /**
     * find the finishing time of a vertex
     * @param graph
     * @param u
     * @param f
     */
    private static void findFinishTime(Graph graph, int u, int[] f) {
        graph.explored[u] = true;
        for (int v : graph.adj[u]) {
            if (!graph.explored[v]) {
                findFinishTime(graph,v,f);
            }
        }
        graph.finishingTime++;
        f[graph.finishingTime] = u;
    }

    /**
     * compute the strongly connected components of a
     * graph using the finishing time of the vertices
     * @param graph
     * @param f
     */
    private static void secondDFSLoop(Graph graph, int[] f) {
        markUnexplored(graph);
        //loop through the finishing time array in decreasing order
        for (int i=f.length-1;i>=0;i--) {
            int u = f[i];
            if (!graph.explored[u]) {
                findStrongConnectedComponents(graph, u);
            }
            System.out.print("\n");
        }
    }

    /**
     * find the strongly connected components of a graph
     * @param graph
     * @param u
     */
    private static void findStrongConnectedComponents(Graph graph, int u) {
        graph.explored[u] = true;
        System.out.print(u + " ");
        for (int v : graph.adj[u]) {
            if (!graph.explored[v]) {
                findStrongConnectedComponents(graph,v);
            }
        }
    }

    /**
     * compute the strongly connected components of a directed graph
     * @param graph
     */
    private static void computeStronglyConnectedComponents(Graph graph) {
        //reverse the edges of the original graph
        Graph revG = reverseGraph(graph);
        //run DFS-Loop on the reverse graph to compute the finishing time
        int[] f = firstDFSLoop(revG);
        //run DFS-Loop on the original graph to compute the strongly connected components
        secondDFSLoop(graph, f);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 3);
        addEdge(graph, 2, 4);
        addEdge(graph, 3, 4);
        addEdge(graph, 3, 5);
        addEdge(graph, 4, 5);
        printGraph(graph);

        System.out.println("Breadth First Search: ");
        BFS(graph, 0);
        System.out.println("Breadth First Search Recursive: ");
        BFSR(graph);

        System.out.println("Shortest path: ");
        findShortestPath(graph, 0, 5);

        Graph graph1 = new Graph(10);
        addEdge(graph1, 0, 2);
        addEdge(graph1, 0, 4);
        addEdge(graph1, 2, 4);
        addEdge(graph1, 4, 6);
        addEdge(graph1, 4, 8);
        addEdge(graph1, 1, 3);
        addEdge(graph1, 5, 7);
        addEdge(graph1, 5, 9);
        System.out.println("Connected Components of an undirected graph:");
        findConnectedComponents(graph1);

        Graph graph2 = new Graph(6);
        addEdge(graph2, 0, 1);
        addEdge(graph2, 0, 2);
        addEdge(graph2, 1, 2);
        addEdge(graph2, 1, 3);
        addEdge(graph2, 2, 4);
        addEdge(graph2, 3, 4);
        addEdge(graph2, 3, 5);
        addEdge(graph2, 4, 5);
        System.out.println("Depth First Search: ");
        DFS(graph2, 0);
        System.out.println("Depth First Search Recursive: ");
        DFSR(graph2);

        Graph graph3 = new Graph(4);
        addDirectedEdge(graph3, 0, 1);
        addDirectedEdge(graph3, 0, 2);
        addDirectedEdge(graph3, 1, 3);
        addDirectedEdge(graph3, 2, 3);
        System.out.println("Topological sort: ");
        printTopologicalSort(graph3);

        Graph graph4 = new Graph(4);
        addDirectedEdge(graph4, 0, 1);
        addDirectedEdge(graph4, 0, 2);
        addDirectedEdge(graph4, 1, 3);
        addDirectedEdge(graph4, 2, 3);
        System.out.println("All Topological sort: ");
        printAllTopologicalSort(graph4);

        Graph graph5 = new Graph(9);
        addDirectedEdge(graph5, 0, 3);
        addDirectedEdge(graph5, 3, 6);
        addDirectedEdge(graph5, 6, 0);
        addDirectedEdge(graph5, 8, 6);
        addDirectedEdge(graph5, 8, 2);
        addDirectedEdge(graph5, 2, 5);
        addDirectedEdge(graph5, 5, 8);
        addDirectedEdge(graph5, 7, 5);
        addDirectedEdge(graph5, 7, 4);
        addDirectedEdge(graph5, 4, 1);
        addDirectedEdge(graph5, 1, 7);
        printGraph(graph5);

        System.out.println("Compute Strongly Connected Components : ");
        computeStronglyConnectedComponents(graph5);
    }
}
