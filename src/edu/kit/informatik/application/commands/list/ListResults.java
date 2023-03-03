package edu.kit.informatik.application.commands.list;

import edu.kit.informatik.data.Data;
import edu.kit.informatik.data.Network;
import edu.kit.informatik.handling.Convert;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains the method which lists the calculated max flows.
 * Data is imported and the class Convert for better handling
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class ListResults {

    // utility-class constructor
    private ListResults() {
    }

    /**
     * This method lists all the calculated results with the vertexes sorted firstly by the the result (int)
     * secondly by the start and target vertex (alphabetically)
     *
     * @param networkToListResults the network of which the user demands the results
     * @return the sorted results in an array of Strings or an array just containing 'EMPTY' if no max flow was c
     * calculated yet for this network.
     */
    public static String[] listCalculatedMaxFlows(Network networkToListResults) {
        if (Data.networkStorage.doesNetworkExist(networkToListResults)) {
            ArrayList<String> flowResults = networkToListResults.getFlowResults();
            if (flowResults.isEmpty()) {
                String[] noResultsCalculated = new String[1];
                noResultsCalculated[0] = "EMPTY";
                return noResultsCalculated;
            }
            Collections.sort(flowResults);                      // sorted with sort method
            return Convert.convertToArray(flowResults);
        } else {
            String[] networkDoesNotExist = new String[1];
            networkDoesNotExist[0] = "Error, this Network was not added yet.";
            return networkDoesNotExist;
        }
    }
}
