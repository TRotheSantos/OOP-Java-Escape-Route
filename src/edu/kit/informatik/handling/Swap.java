package edu.kit.informatik.handling;

import java.util.ArrayList;

/**
 * This class contains the swap method
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class Swap {

    // utility-class constructor
    private Swap() {
    }

    /**
     * method to swap exactly two Strings (f.e. sections) in the given list
     *
     * @param indexA    index of  a String which by order needs to be later in the list
     * @param indexB    (the opposite) the string on this index needs to be earlier on the list
     * @param givenList the list in which two strings need to be swapped
     */
    public static void swapInList(int indexA, int indexB, ArrayList<String> givenList) {
        String sectionA = givenList.get(indexA);
        String sectionB = givenList.get(indexB);
        givenList.remove(indexA);
        givenList.remove(indexB - 1); // because the index A was removed
        givenList.add(indexA, sectionB);
        givenList.add(indexB, sectionA);
    }
}
