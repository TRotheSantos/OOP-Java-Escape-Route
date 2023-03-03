package edu.kit.informatik.application.commands.add;

import edu.kit.informatik.data.Data;

/**
 * This class contains the method which handles the command 'add'
 * Imports Data class to check the existence of network
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class Add {

    // utility-class constructor
    private Add() {
    }

    /**
     * Depending on if the network was already added, the method invokes a method to add the network or a method
     * to only add a section. Both of the methods return a String.
     * This method also checks the amount of the sections which the user wants to add (exactly one is allowed)
     *
     * @param splitInputLine the Terminal input line split and checked in an array of Strings
     * @return a String - An error message if a criteria for adding was violated
     * - Or a message if the network or section was added correctly
     */
    public static String comAdd(String[] splitInputLine) {
        String networkName = splitInputLine[1];
        boolean checkIfNetworkExists = Data.networkStorage.doesNetworkExistByName(networkName);
        if (checkIfNetworkExists) {
            int numberOfArgs = 2;
            if (splitInputLine.length - 1 > numberOfArgs) {  //in case the network already exists, the number of args
                // can not be higher then two, the network name and one section
                return "Error, only one section can be added to existing Network";
            }
            return AddSection.addSection(splitInputLine);
        } else {
            return AddNetwork.addNetwork(splitInputLine);
        }
    }
}
