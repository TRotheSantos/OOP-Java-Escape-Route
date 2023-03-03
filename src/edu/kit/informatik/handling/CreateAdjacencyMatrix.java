package edu.kit.informatik.handling;

import edu.kit.informatik.data.Network;

import java.util.ArrayList;

/**
 * This class contains a method which creates an adjacency matrix out of a given network
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class CreateAdjacencyMatrix {

    // utility-class constructor
    private CreateAdjacencyMatrix() {
    }

    /**
     * Creates an adjacency matrix, for better handling
     *
     * @param network        the network which the adjacency matrix is calculated, with all the necessary data
     * @param splitInputLine the split and checked input line
     * @return the network containing the adjacency matrix
     */
    public static Network createAdjacencyMatrix(Network network, String[] splitInputLine) {
        int capacity;
        String networkName = network.getName();
        int[][] adjacencyMat = network.getAdjacencyMatrix();
        ArrayList<String> vertexList = network.getVertexList();
        int adjacencyRow;
        int adjacencyColumn;
        for (int i = 2; i < splitInputLine.length; i++) {
            String[] edge = splitInputLine[i].split("\\d+");
            String vertexOut = edge[0];
            adjacencyRow = vertexList.indexOf(vertexOut);
            String vertexIn = edge[1];
            adjacencyColumn = vertexList.indexOf(vertexIn);
            try {
                capacity = Integer.parseInt(splitInputLine[i].replaceAll("[^0-9]", ""));
            } catch (NumberFormatException e) {
                return null;
            }
            if (capacity == 0) {
                return null;
            }

            adjacencyMat[adjacencyRow][adjacencyColumn] = capacity;
        }
        network.setAdjacencyMatrix(adjacencyMat);
        return network;
    }

}