package cs311.hw7;

import java.util.*;

/**
 * A simple generic graph data structure in which every vertex
 * in the graph is identified by a String label. This graph also
 * supports attaching an arbitrary data object to each vertex and
 * edge.
 *
 * @param <S> The type of data stored in every VERTEX.
 * @param <T> The type of data stored in every EDGE.
 */
public class HashGraph<S, T> {
    private boolean isDirected = false;

    private Map<String, Vertex<S, T>> labelToVertex = new HashMap<>();

    public HashGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     * If this returns true, the graph is a directed
     * graph. If false, it is undirected.
     */
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Adds a new vertex to the graph that is identified by the
     * label and has the given vertex data attached to it.
     *
     * @param vertexLabel The identifying label of the graph.
     * @param vertexData  The data attached to the vertex.
     */
    public void addVertex(String vertexLabel, S vertexData) {
        Vertex<S, T> v = new Vertex<>(vertexLabel, vertexData);
        labelToVertex.put(vertexLabel, v);
    }

    /**
     * Removes the vertex and all edges associated with it
     * from the graph.
     *
     * @param vertexLabel The vertex to be removed.
     */
    public void removeVertex(String vertexLabel) {
        if (!labelToVertex.containsKey(vertexLabel)) {
            return;
        }

        Vertex toRemove = labelToVertex.get(vertexLabel);
        labelToVertex.remove(toRemove.label);

        for (Vertex v : labelToVertex.values()) {
            v.removeVertex(toRemove);
        }
    }

    /**
     * Adds an edge to the graph from the source vertex to the
     * target vertex. The edge also has the given data stored
     * in it.
     * <p/>
     * If the graph is undirected, both vertices become
     * neighbors of one another. If it is directed, the target
     * vertex is added as a neighbor to source.
     *
     * @param sourceLabel The label of the source vertex of
     *                    the edge.
     * @param targetLabel The label of the target vertex of
     *                    the edge.
     * @param edgeData    The data attached to the edge.
     */
    public void addEdge(String sourceLabel, String targetLabel, T edgeData) {
        if (null == sourceLabel || null == targetLabel || null == edgeData) {
            throw new IllegalArgumentException("Add edge cannot take any null arguments");
        }

        if (!labelToVertex.containsKey(sourceLabel) || !labelToVertex.containsKey(targetLabel)) {
            throw new IllegalArgumentException("Vertices must already be in graph to add edge");
        }

        Vertex<S, T> source = labelToVertex.get(sourceLabel);
        Vertex<S, T> dest = labelToVertex.get(targetLabel);

        source.addEdge(new Edge<>(source, dest, edgeData));

        if (!isDirected()) {
            dest.addEdge(new Edge<>(dest, source, edgeData));
        }
    }

    /**
     * Returns the edge data associated with this edge.
     *
     * @param sourceLabel The source vertex of the edge.
     * @param targetLabel The target vertex of the edge.
     */
    public T getEdgeData(String sourceLabel, String targetLabel) {
        if (null == sourceLabel || null == targetLabel) {
            throw new NullPointerException("getEdgeData cannot have null arguments");
        }

        if (!labelToVertex.containsKey(sourceLabel) || !labelToVertex.containsKey(targetLabel)) {
            throw new IllegalArgumentException("Vertices must already be in graph to get edge data");
        }

        Vertex<S, T> source = labelToVertex.get(sourceLabel);
        Vertex<S, T> dest = labelToVertex.get(targetLabel);

        if (!source.vertexToEdge.containsKey(dest)) {
            throw new IllegalArgumentException("No edge from " + source.label + " to " + dest.label);
        }

        Map<Vertex<S, T>, Edge<T>> vertexToEdge = source.vertexToEdge;
        return vertexToEdge.get(dest).data;
    }

    /**
     * Returns the vertex data associated with this vertex.
     *
     * @param label The label of the vertex.
     */
    public S getVertexData(String label) {
        if (null == label) {
            throw new NullPointerException("Label cannot be null in getVertexData");
        }

        if (!labelToVertex.containsKey(label)) {
            throw new IllegalArgumentException("No vertex named " + label);
        }

        return labelToVertex.get(label).data;
    }

    /**
     * Returns the number of vertices in the graph.
     */
    public int getNumVertices() {
        return labelToVertex.size();
    }

    /**
     * Returns the number of edges in the graph.
     */
    public int getNumEdges() {
        int total = 0;

        for (Vertex v : labelToVertex.values()) {
            total += v.vertexToEdge.size();
        }

        return total;
    }

    /**
     * Returns a collection of the labels of all the vertices
     * in the graph.
     */
    public Collection<String> getVertices() {
        return labelToVertex.keySet();
    }

    /**
     * Returns a collection of all the adjacent vertices of the
     * given vertex.
     *
     * @param label The label of the vertex.
     */
    public Collection<String> getNeighbors(String label) {
        if (null == label) {
            throw new NullPointerException("Cannot get neighbors of null label");
        }

        if (!labelToVertex.containsKey(label)) {
            throw new IllegalArgumentException("Vertex \"" + label + "\" is not in the graph");
        }

        Collection<Vertex<S, T>> outgoingVertices = labelToVertex.get(label).getNeighbors();
        ArrayList<String> labels = new ArrayList<>();

        for (Vertex<S, T> v : outgoingVertices) {
            labels.add(v.label);
        }

        return labels;
    }

    /**
     * Returns a valid topological sort if the graph is a
     * directed acyclic graph and returns null otherwise.
     */
    public List<String> topologicalSort() {
        List<Vertex<S, T>> toReturnReversed = new ArrayList<>();
        Set<Vertex<S, T>> toProcess = new HashSet<>(labelToVertex.values());
        Set<Vertex<S, T>> processed = new HashSet<>();

        while (processed.size() < getNumVertices()) {
            Vertex<S, T> toCheck = toProcess.iterator().next();
            boolean hasCycle = tarjanDFS(toCheck, toProcess, processed, toReturnReversed);

            if (hasCycle) {
                return null;
            }
        }
        List<String> toReturn = new ArrayList<>();
        for (int i = toReturnReversed.size() - 1; i >= 0; i--) {
            toReturn.add(toReturnReversed.get(i).label);
        }

        return toReturn;
    }

    public boolean tarjanDFS(Vertex<S, T> startVertex, Set<Vertex<S, T>> toProcess, Set<Vertex<S, T>> processed,
                             List<Vertex<S, T>> toReturnReversed) {

        Set<Vertex<S, T>> discovered = new HashSet<>();
        Stack<Vertex<S, T>> stack = new Stack<>();
        stack.push(startVertex);

        while(!stack.empty()) {
            Vertex<S, T> curVertex = stack.pop();

            if (discovered.contains(curVertex)) {
                return false;
            }
            if (!processed.contains(curVertex)) {
                discovered.add(curVertex);

                for (Vertex<S, T> v: curVertex.getNeighbors()) {
                    stack.push(v);
                }
            }

            processed.add(curVertex);
            discovered.remove(curVertex);
            toReturnReversed.add(curVertex);
        }
        return true;
    }

    /**
     * Returns the shortest path from the start vertex to
     * the destination vertex where "short" is defined by
     * the edge measure object. The path is a list of the
     * vertex labels in the path starting with the start
     * vertex and ending with the end vertex.
     *
     * @param startLabel Starting vertex label.
     * @param destLabel  Destination vertex label.
     * @param measure    Measure that defines the weight
     *                   of every edge in the graph.
     * @return The shortest path from start to destination.
     */
    public List<String> shortestPath(String startLabel, String destLabel, EdgeMeasure<T> measure) {
        if (null == startLabel || null == destLabel || null == measure) {
            throw new NullPointerException("All parameters to shortestPath must be non-null");
        }

        if (!labelToVertex.containsKey(startLabel) || !labelToVertex.containsKey(destLabel)) {
            throw new IllegalArgumentException("For method shortestPath, both vertices must be in the graph");
        }

        return null;
    }

    /**
     * Returns a minimum spanning tree for the graph with
     * respect to the edge measure object. This tree is returned
     * as a undirected graph.
     *
     * @param measure that defines the weight of every edge
     *                in the graph.
     */
    public Graph<S, T> minimumSpanningTree(EdgeMeasure<T> measure) {
        if (null == measure) {
            throw new NullPointerException("Cannot have null measure in minimumSpanningTree");
        }

        return null;
    }

    /**
     * Computes the total cost of the graph. The total cost is
     * the sum of the costs of every edge in the graph.
     *
     * @param measure The measure for how to determine the cost
     *                of an edge.
     */
    public double getTotalCost(EdgeMeasure<T> measure) {
        double totalCost = 0.0;

        for (Vertex<S, T> v : labelToVertex.values()) {
            for (Edge<T> e : v.vertexToEdge.values()) {
                totalCost += measure.getCost(e.data);
            }
        }

        return totalCost;
    }
}