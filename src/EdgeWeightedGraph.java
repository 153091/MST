import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private List<Edge>[] adj;

    // create an empty Graph with V vertices
    public EdgeWeightedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (List<Edge>[]) new List[V];
        for (int v = 0; v < V; v++)
            adj[v] = new LinkedList<>();
    }

    // create Graph from input stream
    public EdgeWeightedGraph(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");

        try {
            V = in.readInt();
            adj = (List<Edge>[]) new List[V];
            for (int v = 0; v < V; v++)
                adj[v] = new LinkedList<>();

            E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                Edge e = new Edge(v, w ,weight);
                addEdge(e);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in constructor", e);
        }
    }

    // add edge
    public void addEdge(Edge e) {}

    // edges incident to v
    public Iterable<Edge> adj(int v) {}

    // all edges in the graph
    public Iterable<Edge> edges() {}

    // number of vertices
    public int V() {}

    // number of edges
    public int E() {}

    // string representation
    public String toString() {}

    public static void main(String[] args) {

    }
}
