package edu.kit.informatik.handling;

import edu.kit.informatik.data.Network;

import java.util.ArrayList;

/**
 * This class contains the checkGraph method which checks all criteria for if a graph is correct.
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class CheckGraph {

    // utility-class constructor
    private CheckGraph() {
    }

    /**
     * Method which checks all the criteria with help of some private methods for each criteria.
     *
     * @param network the network, which needs to be checked before adding
     * @return String with a specified error message, or an empty String if the graph of the network is correct
     */
    public static String checkGraph(Network network) {
        ArrayList<String> vertexList = network.getVertexList();
        int[][] adjacencyMatrix = network.getAdjacencyMatrix();
        ArrayList<String> startVertexList = new ArrayList<>();
        ArrayList<String> targetVertexList = new ArrayList<>();

        for (String vertex : vertexList) {
            if (network.isStartVertex(vertex)) {
                startVertexList.add(vertex);
            }
            if (network.isTargetVertex(vertex)) {
                targetVertexList.add(vertex);
            }
        }

        if (areThereLoops(adjacencyMatrix)) {
            return "Error, sections with just one vertex are not allowed (loops)";
        }

        if (areThereReverseSections(adjacencyMatrix)) {
            return "Error, reverse sections are not allowed.";
        }

        if ((startVertexList.size() == 0) || (targetVertexList.size() == 0)) {
            return "Error, no start or no target vertex in the network.";
        }

        if (isNoFlowPath(startVertexList, targetVertexList, network)) {
            return "Error, there is no maxflow in the network";
        }

        return "";
    }

    /**
     * Method to check if there are loops in a graph
     *
     * @param adjacencyMatrix which needs to be checked, representing the graph
     * @return true: if there is min one loop, false: if the graph is 'loop-free'
     */
    private static boolean areThereLoops(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][i] > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if there are reverse sections in a graph
     *
     * @param adjacencyMatrix which needs to be checked, representing the graph
     * @return true: if there is min one reverse section, false: if not
     */
    private static boolean areThereReverseSections(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] > 0 && adjacencyMatrix[j][i] > 0) {   // capacity is both over zero
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method which checks if calculating the flow is possible
     *
     * @param startVertexList  Array list of strings with the start vertexes
     * @param targetVertexList Array list of strings with the target vertexes
     * @param network          the network which is checked
     * @return true: if no flow path is possible, false: if it is possible
     */
    private static boolean isNoFlowPath(ArrayList<String> startVertexList,
                                        ArrayList<String> targetVertexList, Network network) {
        int[][] adjacencyMatrix = network.getAdjacencyMatrix();
        ArrayList<String> vertexList = network.getVertexList();
        for (String startVertex : startVertexList) {
            for (String targetVertex : targetVertexList) {
                String path = BreadthFirstSearch.breadthFirstSearch(
                        startVertex, targetVertex, vertexList, adjacencyMatrix);
                if (!path.equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}
