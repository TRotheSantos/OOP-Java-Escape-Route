package edu.kit.informatik.data;

import edu.kit.informatik.userinterface.CommandPattern;

/**
 * The data class
 * Contains the network storage, where the networks are stored and a public constructor
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class Data {

    /**
     * This is the network storage, where all added networks of a session are stored.
     */
    public static NetworkStorage networkStorage = new NetworkStorage();

    // utility-class constructor
    private Data() {
    }

    /**
     * public constructor to initialize the command patterns in the main method
     */
    public static void initData() {
        CommandPattern.defineCommandPattern();
    }

}


