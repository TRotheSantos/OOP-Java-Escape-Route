package edu.kit.informatik.application.commands.list;

import edu.kit.informatik.data.Data;
import edu.kit.informatik.data.Network;


/**
 * This class contains the method which handles the command 'list'
 * Imports Data class to check the existence of network and Network class to create a reference for the network which
 * the user demands the results
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class List {

    // utility-class constructor
    private List() {
    }

    /**
     * Depending on if the user puts a network name after the command, the method invokes a method to list all existing
     * networks or a method to list the results of the calculated max flows for the given network name
     * to only add a section. Both of the methods return a String array.
     *
     * @param splitInputLine the Terminal input line split and checked in an array of Strings
     * @return a String array - with the demanded data
     * - or the array containing 'EMPTY' if no data is available
     */
    public static String[] comList(String[] splitInputLine) {
        if (splitInputLine.length == 1) {      // just the 'list' command is in the input line
            return ListNetworks.listAddedNetworks();
        } else {
            String lookForName = splitInputLine[1];
            Network networkToListResults = Data.networkStorage.getNetwork(lookForName);
            String[] results = ListResults.listCalculatedMaxFlows(networkToListResults);
            return results;
        }
    }
}
