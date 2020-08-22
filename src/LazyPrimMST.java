/** tinyEWG.txt
 0-7 0,16000
 1-7 0,19000
 0-2 0,26000
 2-3 0,17000
 5-7 0,28000
 4-5 0,35000
 6-2 0,40000
 1.81
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;

public class LazyPrimMST {
    private double weight; // weight of the MST
    private List<Edge> mst; // edges of MST
    private MinPQ<Edge> pq; // edges with one endpoint in tree
    private boolean[] marked; // vertices of MST

    // constructor
    public LazyPrimMST(EdgeWeightedGraph G) {
        mst = new LinkedList<>();
        marked = new boolean[G.V()];
        pq = new MinPQ<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) prim(G, v);
        }
    }

    private void prim(EdgeWeightedGraph G, int vertex) {
        visit(G, vertex);
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue; // lazy, both v and w already scanned
            mst.add(e);
            weight += e.weight();
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int vertex) {
        marked[vertex] = true;
        for (Edge e : G.adj(vertex))
            if (!marked[e.other(vertex)])
                pq.insert(e);
    }

    // edges of MST
    public Iterable<Edge> mst() {
        return mst;
    }

    // weight of the MST
    public double weight() {
        return weight;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        LazyPrimMST MST = new LazyPrimMST(G);

        for (Edge e : MST.mst())
            StdOut.println(e);
        StdOut.println(MST.weight());
    }
}
