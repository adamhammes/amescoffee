package cs311.hw7;

import java.util.Comparator;

public class EdgeComparator<S,T> implements Comparator<Edge<S, T>> {
    public EdgeMeasure<T> edgeMeasure;

    public EdgeComparator(EdgeMeasure<T> edgeMeasure) {
        this.edgeMeasure = edgeMeasure;
    }

    @Override
    public int compare(Edge<S, T> edge1, Edge<S, T> edge2) {
        return Double.compare(edgeMeasure.getCost(edge1.data), edgeMeasure.getCost(edge2.data));
    }
}
