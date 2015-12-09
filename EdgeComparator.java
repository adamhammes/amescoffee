package cs311.hw7;

import java.util.Comparator;
import java.util.Map;

public class EdgeComparator<S, T> implements Comparator<Vertex<S, T>> {
    private Map<Vertex<S, T>, Double> cost;

    public EdgeComparator(Map<Vertex<S, T>, Double> cost) {
        this.cost = cost;
    }

    @Override
    public int compare(Vertex<S, T> v1, Vertex<S, T> v2) {
        return Double.compare(cost.get(v1), cost.get(v2));
    }
}
