package edu.kit.informatik.handling;

import java.util.ArrayList;

/**
 * This class contains the define vertexes method.
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class DefineVertexes {

    // utility-class constructor
    private DefineVertexes() {
    }

    /**
     * Method to extract the vertexes of an array with sections and store the vertexes in a list
     * so all vertexes have an index
     *
     * @param vertexList the array list of all vertexes
     * @param splitInputLine is a string array
     */
    public static void vertexesOfNetwork(ArrayList<String> vertexList, String[] splitInputLine) {
        for (int i = 2; i < splitInputLine.length; i++) {
            String[] section = splitInputLine[i].split("\\d+");
            if (!(vertexList).contains(section[0])) {                   //section[0]=startVertex of the edge
                vertexList.add(section[0]);
            }
            if (!(vertexList).contains(section[1])) {                   //section[1]=targetVertex of the edge
                vertexList.add(section[1]);
            }
        }
    }

}
