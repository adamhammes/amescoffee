package cs311.hw7;

public class Edge<S, T> {
    public T data;
    public Vertex<S, T> source;
    public Vertex<S, T> dest;

    public Edge(Vertex<S, T> source, Vertex<S, T> dest, T data) {
        this.source = source;
        this.dest = dest;
        this.data = data;
    }
}
