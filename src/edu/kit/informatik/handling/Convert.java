package edu.kit.informatik.handling;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contain two methods to convert, which are only for better handling in some points of the program
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class Convert {

    // utility-class constructor
    private Convert() {
    }

    /**
     * method to convert a ArrayList into an array, for better handling
     *
     * @param listName the list of Strings
     * @return an String array with exact same data as the list
     */
    public static String[] convertToArray(ArrayList<String> listName) {
        String[] arrayName = new String[listName.size()];
        for (int i = 0; i < listName.size(); i++) {
            arrayName[i] = listName.get(i);
        }
        return arrayName;
    }

    /**
     * method to convert an array into an ArrayList, for better handling
     *
     * @param arrayName array of Strings
     * @return an ArrayList with the data of the String
     */
    public static ArrayList<String> convertToList(String[] arrayName) {
        return new ArrayList<>(Arrays.asList(arrayName));
    }
}
