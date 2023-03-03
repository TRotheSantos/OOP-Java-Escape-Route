package edu.kit.informatik.data;


import java.util.ArrayList;

/**
 * This class is to create objects from the type 'Network', it contains all the necessary criteria and some methods
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public class Network {

    private final String name;
    private ArrayList<String> vertexList;
    private int[][] adjacencyMatrix;
    private ArrayList<String> flowResults;

    /**
     * constructor to create a network
     *
     * @param name            a String with the name of the network
     * @param vertexList      an array list with the vertexes of the graph
     * @param adjacencyMatrix an 2D array with the adjacency matrix of the graph, for more efficiency
     */
    public Network(String name, ArrayList<String> vertexList, int[][] adjacencyMatrix) {
        this.name = name;
        this.vertexList = vertexList;
        this.adjacencyMatrix = adjacencyMatrix;
        this.flowResults = new ArrayList<>();
    }

    /**
     * gets the name of a network
     *
     * @return a String with the name
     */
    public String getName() {
        return name;
    }

    /**
     * gets the adjacency matrix of a network
     *
     * @return a 2D array with the adjacency matrix
     */
    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * gets the vertex list of a network
     *
     * @return an array list of the vertexes of a network
     */
    public ArrayList<String> getVertexList() {
        return vertexList;
    }

    /**
     * get the calculated flow results of a network
     *
     * @return an array list of the results
     */
    public ArrayList<String> getFlowResults() {
        return flowResults;
    }

    /**
     * method to add a String to the result list
     *
     * @param startV         the start vertex
     * @param targetV        the target vertex
     * @param valueOfMaxFlow the result
     */
    public void addToResultList(String startV, String targetV, String valueOfMaxFlow) {
        String resultLine = valueOfMaxFlow + " " + startV + " " + targetV;
        this.flowResults.add(resultLine);
    }

    /**
     * method to check if a vertex is really a start vertex
     *
     * @param vertex String with the vertex to check
     * @return true: if the vertex is a start vertex, false: if not
     */
    public boolean isStartVertex(String vertex) {
        if (!vertexList.contains(vertex)) {
            return false;
        }
        int indexOfVertex = vertexList.indexOf(vertex);
        int edgePrevious = 0;

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            edgePrevious = edgePrevious + adjacencyMatrix[i][indexOfVertex];
        }
        if (!(edgePrevious == 0)) {
            return false;
        }
        return true;
    }

    /**
     * method to check if a vertex is really a target vertex
     *
     * @param vertex String with the vertex to check
     * @return true: if the vertex is a target vertex, false: if not
     */
    public boolean isTargetVertex(String vertex) {
        if (!vertexList.contains(vertex)) {
            return false;
        }
        int indexOfVertex = vertexList.indexOf(vertex);
        int edgeFollower = 0;

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            edgeFollower = edgeFollower + adjacencyMatrix[indexOfVertex][i];
        }
        if (!(edgeFollower == 0)) {
            return false;
        }
        return true;
    }

    /**
     * set-method to overwrite the adjacency matrix of a network
     *
     * @param adjacencyMatrix the new adjacency matrix
     */
    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    /**
     * set-method to overwrite the vertex list
     *
     * @param vertexList the list of vertexes of a graph
     */
    public void setVertexList(ArrayList<String> vertexList) {
        this.vertexList = vertexList;
    }
}
