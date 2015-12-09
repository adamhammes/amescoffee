package cs311.hw7;

public class DoubleMeasure implements EdgeMeasure<Double> {

    /**
     * Computes the cost (sometimes referred to as the
     * weight) of the edge.
     *
     * @param edgeData
     */
    @Override
    public double getCost(Double edgeData) {
        return edgeData;
    }
}
