package edu.kit.informatik.application.commands.add;

import edu.kit.informatik.data.Data;
import edu.kit.informatik.data.Network;
import edu.kit.informatik.handling.CheckGraph;
import edu.kit.informatik.handling.CreateAdjacencyMatrix;
import edu.kit.informatik.handling.DefineVertexes;

import java.util.ArrayList;

/**
 * This class contains the method which creates the network with the given data in the input, checks all criteria and
 * adds the new network to the storage, with the help of some private methods.
 *
 * @author Tilmann Rothe Santos#
 * @version 1.0
 */
public final class AddNetwork {

    //utility-class constructor
    private AddNetwork() {
    }

    /**
     * This method adds a new network to the network storage.
     *
     * @param splitInputLine given from the method add
     * @return String with specified error message or the message that network was added correctly
     */
    public static String addNetwork(String[] splitInputLine) {
        if (areThereDuplicatedEdges(splitInputLine)) {
            return "Error, duplicated edges as arguments.";
        }
        ArrayList<String> vertexList = new ArrayList<>();
        DefineVertexes.vertexesOfNetwork(vertexList, splitInputLine);                   // list containing all the
        // vertexes for better handling
        if (isThereLessThanTwoSections(vertexList)) {
            return "Error, to add a new Network there must be minimum two sections";
        }
        int[][] adjacencyMatrix = new int[vertexList.size()][vertexList.size()];
        Network network = new Network(splitInputLine[1], vertexList, adjacencyMatrix);
        network = CreateAdjacencyMatrix.createAdjacencyMatrix(network, splitInputLine); // constructs new network with
        // the right adjacency-matrix
        if (network == null) {
            return "Error, invalid capacity. Capacity must be higher than zero and smaller than INT_MAX.";
        }
        String errMessage = CheckGraph.checkGraph(network);
        if (errMessage.equals("")) {
            // if additionally the network fulfills the graph criteria, it is added to the network storage
            Data.networkStorage.addNetworkToStorage(network);
        } else {
            return errMessage; // if not returns specific error message
        }
        return "Added new escape network with identifier " + network.getName() + ".";
    }

    /**
     * Private method to check if more than one section
     *
     * @param vertexList
     * @return
     */
    private static boolean isThereLessThanTwoSections(ArrayList<String> vertexList) {
        return vertexList.size() < 3;
    }

    /**
     * Private method to check if the user entered duplicated edges
     *
     * @param splitInputLine the input array
     * @return true: if there are duplicated edges and false: if all edges are unique
     */
    private static boolean areThereDuplicatedEdges(String[] splitInputLine) {
        ArrayList<String[]> edges = new ArrayList<>();
        for (int i = 2; i < splitInputLine.length; i++) {
            edges.add(splitInputLine[i].split("\\d+"));  // cuts out the capacity (int value) so the sections
            // becomes an edge
        }
        while (edges.size() > 1) {
            String[] edge = edges.get(0);
            edges.remove(0);                             // removes the first edge
            for (String[] s : edges) {                        // compare all edged with the first one
                if (s[0].equals(edge[0]) && s[1].equals(edge[1])) {
                    return true;
                }
            }

        }
        return false;
    }
}


