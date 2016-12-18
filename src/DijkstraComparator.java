package cs311.hw7;

import java.util.Comparator;
import java.util.Map;

public class DijkstraComparator<S, T> implements Comparator<Vertex<S, T>> {
    private Map<Vertex<S, T>, Double> costs;

    public DijkstraComparator(Map<Vertex<S, T>, Double> costs) {
        this.costs = costs;
    }

    @Override
    public int compare(Vertex<S, T> v1, Vertex<S, T> v2) {
        return Double.compare(costs.get(v1), costs.get(v2));
    }
}
