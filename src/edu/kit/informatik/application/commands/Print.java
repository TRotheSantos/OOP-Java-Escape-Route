package edu.kit.informatik.application.commands;

import edu.kit.informatik.data.Data;
import edu.kit.informatik.data.Network;
import edu.kit.informatik.handling.Convert;
import edu.kit.informatik.handling.Swap;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains the print method and two private methods.
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class Print {

    // utility-class constructor
    private Print() {
    }

    /**
     * This method gets the sections of a network and order them alphabetically (firstly for out vertex and then
     * in vertex)
     *
     * @param splitInputLine the Terminal input line split and checked in an array of Strings
     * @return an array with the demanded data, or an error message if the network was not added yet
     */
    public static String[] comPrint(String[] splitInputLine) {
        String networkName = splitInputLine[1];
        if (Data.networkStorage.doesNetworkExistByName(networkName)) {
            Network networkToPrint = Data.networkStorage.getNetwork(networkName);
            ArrayList<String> sectionsList = getSections(networkToPrint);
            ArrayList<String> sortedSectionList = inAlphabeticalOrder(sectionsList);
            String[] sortedSections = Convert.convertToArray(sortedSectionList);
            return sortedSections;
        } else {
            String[] networkDoesNotExist = new String[1];
            networkDoesNotExist[0] = "Error, this Network was not added yet.";
            return networkDoesNotExist;
        }

    }

    /**
     * Private method to convert a String Array in a list of Strings, ordered alphabetically
     *
     * @param sectionsList contains sections (outVertex+capacity+inVertex) in one String
     * @return list of the sections ordered alphabetically
     */
    private static ArrayList<String> inAlphabeticalOrder(ArrayList<String> sectionsList) {
        // sort the outVertexes alphabetically
        Collections.sort(sectionsList);
        // if the outVertexes are identical, sort the inVertexes alphabetically
        // worst-case: all sections except one, have identical outVertexes, in exact the opposite order
        for (int worstCase = 0; worstCase < sectionsList.size() - 1; worstCase++) {
            for (int i = 0; i < sectionsList.size() - 1; i++) {
                String[] inAndOutVertexesA = sectionsList.get(i).split("\\d+");
                String[] inAndOutVertexesB = sectionsList.get(i + 1).split("\\d+");
                String outVertexA = inAndOutVertexesA[0];
                String outVertexB = inAndOutVertexesB[0];
                String inVertexA = inAndOutVertexesA[1];
                String inVertexB = inAndOutVertexesB[1];
                if (outVertexA.equals(outVertexB)) {                   // if same name of out vertexes
                    if (inVertexA.compareTo(inVertexB) > 0) {          // if the element earlier in the list is
                        // lexicographically higher then swap
                        Swap.swapInList(i, i + 1, sectionsList); // swaps exactly two sections
                    }
                }
            }
        }
        return sectionsList;
    }


    /**
     * Private method to get the sections of a specific Network, by getting the information of adjacency matrix and
     * vertex list.
     *
     * @param networkToPrint the Network which the user wants to have printed the sections
     * @return the sections of a Network in form of a list
     */
    private static ArrayList<String> getSections(Network networkToPrint) {
        int numberOfEdges = 0;
        int[][] adjacencyMatrix = networkToPrint.getAdjacencyMatrix();
        ArrayList<String> sectionsList = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    String vertexOut = networkToPrint.getVertexList().get(i);
                    String capacity = String.valueOf(adjacencyMatrix[i][j]);
                    String vertexIn = networkToPrint.getVertexList().get(j);
                    sectionsList.add(vertexOut + capacity + vertexIn);
                    numberOfEdges++;
                }
            }
        }
        return sectionsList;
    }
}
