/** %java Edge.java
 12-34 5,67000
 */

import edu.princeton.cs.algs4.StdOut;

public class Edge implements Comparable<Edge> {

    private final int v, w;
    private final double weight;

    // constructor
    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        // .isNaN Возвращает значение, показывающее, что указанное значение не является числом (NaN)
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // either vertex
    public int either() {
        return v;
    }

    // other vertex
    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    // weight
    public double weight() {
        return weight;
    }

    // compare to
    public int compareTo(Edge that) {
        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return +1;
        else    return 0;
    }

    // string representation of Edge
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        StdOut.println(e);
    }
}
