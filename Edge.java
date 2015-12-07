package cs311.hw7;

public class Edge<T> {
    public T data;
    public Vertex source;
    public Vertex dest;

    public Edge(Vertex source, Vertex dest, T data) {
        this.source = source;
        this.dest = dest;
        this.data = data;
    }
}
