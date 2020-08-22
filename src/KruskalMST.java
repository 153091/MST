/** src\tinyEWG.txt
 0-7 0,16000
 2-3 0,17000
 1-7 0,19000
 0-2 0,26000
 5-7 0,28000
 4-5 0,35000
 6-2 0,40000
 1.81*/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

import java.util.LinkedList;
import java.util.List;

public class KruskalMST {

    private double weight; // weight of the MST
    private List<Edge> mst = new LinkedList<>(); // edges of MST

    // constructor
    public KruskalMST(EdgeWeightedGraph G) {
        weight = 0;
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e: G.edges())
            pq.insert(e);

        // greedy algorithm
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);
                mst.add(e); // merge v and w components
                weight += e.weight();
            }
        }
    }

    //
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
        KruskalMST MST = new KruskalMST(G);

        for (Edge e : MST.mst())
            StdOut.println(e);
        StdOut.println(MST.weight());
    }
}
