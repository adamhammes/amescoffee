package cs311.hw7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Vertex<S, T> {
    public String label;
    public S data;
    public final Map<Vertex<S, T>, Edge<T>> vertexToEdge = new HashMap<Vertex<S, T>, Edge<T>>();

    public Vertex(String label, S data) {
        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public S getData() {
        return data;
    }

    public void removeVertex(Vertex v) {
        vertexToEdge.remove(v);
    }

    public void addEdge(Edge<T> e) {
        vertexToEdge.put(e.source, e);
    }

    public Collection<Vertex<S, T>> getNeighbors() {
        return vertexToEdge.keySet();
    }
}
