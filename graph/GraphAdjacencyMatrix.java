package graph;

public class GraphAdjacencyMatrix {

    private int vertices;
    private int[][] adj;

    public GraphAdjacencyMatrix(int vertices) {
        this.vertices = vertices;
        adj = new int[this.vertices][this.vertices];
    }

    private void addEdge(int source, int dest) {
        adj[source][dest] = 1;
        adj[dest][source] = 1;
    }

    private void printGraph() {
        System.out.println("Adjacency Matrix Representation");
        for (int i=0;i<this.vertices;i++) {
            for (int j=0;j<this.vertices;j++) {
                if (adj[i][j] != 0) {
                    System.out.println(i + " -> " + j);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(4);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1, 2);
        graph.addEdge(1,3);
        graph.addEdge(2,3);
        graph.printGraph();
    }
}
