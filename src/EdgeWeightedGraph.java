/** src\tinyEWG.txt
 8 16
 0: 0-7 0,16000 0-4 0,38000 0-2 0,26000 6-0 0,58000
 1: 1-5 0,32000 1-7 0,19000 1-2 0,36000 1-3 0,29000
 2: 2-3 0,17000 0-2 0,26000 1-2 0,36000 2-7 0,34000 6-2 0,40000
 3: 2-3 0,17000 1-3 0,29000 3-6 0,52000
 4: 4-5 0,35000 4-7 0,37000 0-4 0,38000 6-4 0,93000
 5: 4-5 0,35000 5-7 0,28000 1-5 0,32000
 6: 6-2 0,40000 3-6 0,52000 6-0 0,58000 6-4 0,93000
 7: 4-7 0,37000 5-7 0,28000 0-7 0,16000 1-7 0,19000 2-7 0,34000 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class EdgeWeightedGraph {

    private static final String NEWLINE = System.getProperty("line.separator");

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

            int E = in.readInt();
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
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    // edges incident to v
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    // degree of vertex v
    public int degree(int v) {
        return adj[v].size();
    }

    // all edges in the graph
    public Iterable<Edge> edges() {
        List<Edge> list = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            int selfloops = 0;
            for (Edge e: adj[v]) {
                if (e.other(v) > v)
                    list.add(e);
                // add only one copy of each self loop (self loops will be consecutive)
                // добавляем каждый второй селфлуп, чтобы только v-v*, but not v*-v
                else if (e.other(v) == v) {
                    if (selfloops % 2 == 0) list.add(e);
                    selfloops++;
                }
            }
        }
        return list;
    }

    // number of vertices
    public int V() {
        return V;
    }

    // number of edges
    public int E() {
        return E;
    }

    // string representation
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e: adj[v])
                s.append(e + " ");
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        StdOut.println(G);
    }
}
