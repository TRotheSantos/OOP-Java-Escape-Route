package edu.kit.informatik.application.commands.add;

import edu.kit.informatik.data.Data;
import edu.kit.informatik.data.Network;
import edu.kit.informatik.handling.CheckGraph;
import edu.kit.informatik.handling.CreateAdjacencyMatrix;

import java.util.ArrayList;

/**
 * This class contains the method which adds a section, given from the user, to an existing network (with the help of
 * some private methods)
 * After checking criteria the network gets a new adjacency matrix
 *
 * @author Tilmann Rothe Santos#
 * @version 1.0
 */
public final class AddSection {

    // utility-class constructor
    private AddSection() {
    }

    /**
     * This method adds a section to an existing network by calculating the new adjacency matrix and setting it to
     * the existing network.
     *
     * @param splitInputLine given from the method add
     * @return String with specified error message or the message that section was added correctly
     */
    public static String addSection(String[] splitInputLine) {
        String networkName = splitInputLine[1];
        Network network = Data.networkStorage.getNetwork(networkName);
        ArrayList<String> vertexList = new ArrayList<>(network.getVertexList());
        int[][] adjacencyMatrix = new int[network.getAdjacencyMatrix().length][];
        for (int i = 0; i < network.getAdjacencyMatrix().length; i++) {
            adjacencyMatrix[i] = network.getAdjacencyMatrix()[i].clone();
        }
        toExtendVertexList(splitInputLine[2], vertexList);
        if (adjacencyMatrix.length != vertexList.size()) {                                  // must be the same size
            adjacencyMatrix = toEnlargeAdjacencyMatrix(adjacencyMatrix, vertexList.size());
        }
        network = new Network(networkName, vertexList, adjacencyMatrix);
        network = CreateAdjacencyMatrix.createAdjacencyMatrix(network, splitInputLine);
        if (network == null) {
            return "Error, invalid capacity. Capacity must be higher than zero and smaller than INT_MAX.";
        }
        String errMessage = CheckGraph.checkGraph(network);
        if (errMessage.equals("")) {
            // if all criteria is fulfilled, the new adjacency matrix is the adjacency matrix for the existing network
            Data.networkStorage.getNetwork(networkName).getFlowResults().clear();
            Data.networkStorage.getNetwork(networkName).setAdjacencyMatrix(adjacencyMatrix);
            Data.networkStorage.getNetwork(networkName).setVertexList(vertexList);
        } else {
            return errMessage;
        }
        return "Added new section " + splitInputLine[2] + " to escape network " + networkName + ".";
    }

    /**
     * Private method to extend the vertex list
     *
     * @param sectionToBeAdded the section, to extract the vertexes
     * @param vertexList       the vertex list
     */
    private static void toExtendVertexList(String sectionToBeAdded, ArrayList<String> vertexList) {
        String[] edge = sectionToBeAdded.split("\\d+");
        if (!vertexList.contains(edge[0])) {                    // start vertex
            vertexList.add(edge[0]);
        }
        if (!vertexList.contains(edge[1])) {                    // target vertex
            vertexList.add(edge[1]);
        }
    }

    /**
     * Private method to enlarge an adjacency matrix
     *
     * @param adjacencyMatrix the matrix to enlarge
     * @param newSize         the new size of the array/matrix
     * @return the new enlarged matrix
     */
    private static int[][] toEnlargeAdjacencyMatrix(int[][] adjacencyMatrix, int newSize) {
        int[][] newAdjacencyMatrix = new int[newSize][newSize];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];  // array copy
            }
        }
        return newAdjacencyMatrix;
    }
}
