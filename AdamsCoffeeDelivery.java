package cs311.hw7;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdamsCoffeeDelivery implements CoffeeTask {


    /**
     * You must construct a graph representing the ingredient
     * dependencies specified in the homework and then use
     * topological sort to find a valid sorting.
     * <p/>
     * The list returned is the list of vertex ids of the locations
     * of each ingredient in the valid sorting.
     */
    @Override
    public List<Integer> getSortedIngredientLocations() {
        return null;
    }

    /**
     * Given a File to the Ames data and an ordering of
     * ingredient location vertex ids, you are to parse the Ames file
     * and create a directed graph, then find the shortest route from
     * your location picking up the ingredients in the order specified,
     * and then delivering them to Jim's location.
     * <p/>
     * You are to use the distance provided in each edge of the Ames
     * data as the weights of the edges.
     * <p/>
     * The list returned is the order of vertex ids visited in the
     * shortest path starting with your location and ending with
     * Jim's location.
     *
     * @param amesFile
     * @param intList
     */
    @Override
    public List<Integer> getShortestRoute(File amesFile, List<Integer> intList) {
        return null;
    }

    /**
     * Given a File to the Ames data, you are to parse the file
     * and create an undirected graph, then find a minimum spanning
     * tree of the city, and return the total cost of the spanning
     * tree.
     * <p/>
     * Use the distance of every edge in the Ames file as the cost
     * of the edges. The total cost is the sum of all the edge costs
     * of the edges in the minimum spanning tree.
     *
     * @param amesFile
     */
    @Override
    public double getMSTCost(File amesFile) {
        return 0;
    }

    public Graph<String, Double> readAmesFile(File amesFile) {
        Scanner input;
        try {
            input = new Scanner(amesFile);
        } catch (FileNotFoundException f) {
            return null;
        }
        Graph<String, Double> amesGraph = new HashGraph<>(true);

        int numVertices = getNumVertices(input.nextLine());
        for (int i = 0; i < numVertices; i++) {
            amesGraph.addVertex(readVertex(input.nextLine()), "");
        }

        int numEdges = getNumEdges(input.nextLine());
        for (int i = 0; i < numEdges; i++) {
            String[] data = readEdge(input.nextLine());
            amesGraph.addEdge(data[0], data[1], Double.parseDouble(data[2]));
        }

        return amesGraph;
    }

    private int getNumVertices(String line) {
        assert null != line;

        String regex = "VERTICES: (\\d+)";
        return Integer.parseInt(regex(regex, line)[0]);
    }

    private String readVertex(String line) {
        assert null != line;

        String regex = "(.*),.*";
        return regex(regex, line)[0];
    }

    private int getNumEdges(String line) {
        assert null != line;

        String regex = "EDGES: (\\d+)";
        return Integer.parseInt(regex(regex, line)[0]);
    }

    private String[] readEdge(String line) {
        assert null != line;

        String regex = "(\\d+),(\\d+),([^,]*)";
        return regex(regex, line);
    }

    private String[] regex(String regex, String string) {
        assert null != regex && null != string;

        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(string);

        if (!m.matches()) {
            throw new IllegalArgumentException("Regex \"" + regex + "\" does not match target string \""
                                                + string + "\"");
        }
        String[] matches = new String[m.groupCount()];
        for (int i = 0; i < matches.length; i++) {
            matches[i] = m.group(i);
        }

        return matches;
    }
}
