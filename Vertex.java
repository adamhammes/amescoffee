package cs311.hw7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Vertex<S, T> {
    public String label;
    public S data;
    public final Map<Vertex<S, T>, Edge<S, T>> vertexToEdge = new HashMap<>();

    public Vertex(String label, S data) {
        if (null == label || null == data) {
            throw new NullPointerException("Vertices may not have null label or data");
        }

        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void removeVertex(Vertex v) {
        vertexToEdge.remove(v);
    }

    public void addEdge(Edge<S, T> e) {
        vertexToEdge.put(e.source, e);
    }

    public Collection<Edge<S, T>> outgoingEdges() {
        return vertexToEdge.values();
    }

    public Collection<Vertex<S, T>> getNeighbors() {
        return vertexToEdge.keySet();
    }

    @Override
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }

        if (!(other instanceof Vertex)) {
            return false;
        }

        Vertex v = (Vertex)other;
        return label.equals(v.getLabel());
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}
