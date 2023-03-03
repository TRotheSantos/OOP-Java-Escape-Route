package edu.kit.informatik.application.commands.list;

import edu.kit.informatik.data.Data;
import edu.kit.informatik.data.NetworkStorage;
import edu.kit.informatik.handling.Convert;
import edu.kit.informatik.handling.Swap;

import java.util.ArrayList;

/**
 * This class contains the method that lists all the networks and a private method which order tha data by the above
 * described criteria.
 * Convert and Swap class are imported for handling also Data and NetworkStorage for the data
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class ListNetworks {

    // utility-class constructor
    private ListNetworks() {
    }


    /**
     * The method to list all the added networks including the number of vertexes for each network.
     * Also checks if there is any network added yet
     *
     * @return an array of Strings with the demanded data or an array containing just 'EMPTY'
     */
    public static String[] listAddedNetworks() {
        NetworkStorage networkStorage = Data.networkStorage;
        if (networkStorage.size() == 0) {
            String[] listIsEmpty = new String[1];
            listIsEmpty[0] = "EMPTY";
            return listIsEmpty;
        }
        int numberOfNetworks = networkStorage.size();
        String[] nameAndNumber = new String[numberOfNetworks];
        for (int i = 0; i < numberOfNetworks; i++) {
            String name = networkStorage.get(i).getName();
            String numberV = String.valueOf(networkStorage.get(i).getVertexList().size());
            nameAndNumber[i] = name + " " + numberV;
        }
        String[] sortedNamesAndNumbers = reorder(nameAndNumber);
        return sortedNamesAndNumbers;
    }

    /**
     * Private method to sort an array by the number of vertexes, from high to low.
     * If the number of vertexes is equal, the Strings are ordered alphabetically.
     *
     * @param nameAndNumber an String array: each String consists of the network and the number of vertexes
     * @return the same array but ordered by the criteria
     */
    private static String[] reorder(String[] nameAndNumber) {
        // for better handling convert to a list
        ArrayList<String> listNameAndNumber = Convert.convertToList(nameAndNumber);
        for (int worstCase = 0; worstCase < nameAndNumber.length; worstCase++) {
            for (int i = 0; i < listNameAndNumber.size() - 1; i++) {
                String[] listAndNumberA = listNameAndNumber.get(i).split(" ");
                String[] listAndNumberB = listNameAndNumber.get(i + 1).split(" ");
                String nameA = String.valueOf(listAndNumberA[0]);
                String nameB = String.valueOf(listAndNumberB[0]);
                Integer numberVertexesA = Integer.parseInt(listAndNumberA[1]);
                Integer numberVertexesB = Integer.parseInt(listAndNumberB[1]);
                switch (numberVertexesA.compareTo(numberVertexesB)) {
                    case -1:  // if the number of vertexes of the element earlier in the list is smaller than the number
                        // of vertexes of the next element
                        Swap.swapInList(i, i + 1, listNameAndNumber);
                        break;
                    case 0:   // if the number is equal, compare the names lexicographically
                        if (nameA.compareTo(nameB) > 0) { // if the 'earlier element' is lexicographically higher
                            Swap.swapInList(i, i + 1, listNameAndNumber);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        String[] nameAndNumberSorted = Convert.convertToArray(listNameAndNumber);
        return nameAndNumberSorted;
    }
}