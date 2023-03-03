package edu.kit.informatik.userinterface;

/**
 * This class contains the command patterns for every command
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class CommandPattern {

    /**
     * This ist the final number of different commands of the program (add, list, flow, print and quit)
     */
    static final int NUMBER_OF_COMMANDS = 5;

    /**
     * This is the unchangeable syntax of the commands
     */
    static final String[] SYNTAX_COMMANDS = new String[NUMBER_OF_COMMANDS];

    // utility-class constructor
    private CommandPattern() {
    }

    /**
     * This method is invoked in the initialize data method and sets all pattern in an array
     */
    public static void defineCommandPattern() {
        SYNTAX_COMMANDS[0] = "^add\\x20[A-Z]{1,6}\\x20[a-z]{1,6}\\d+[a-z]{1,6}(;[a-z]{1,6}\\d+[a-z]{1,6})*";
        SYNTAX_COMMANDS[1] = "^list(\\x20[A-Z]{1,6}){0,1}";
        SYNTAX_COMMANDS[2] = "^print\\x20[A-Z]{1,6}";
        SYNTAX_COMMANDS[3] = "^flow\\x20[A-Z]{1,6}\\x20[a-z]{1,6}\\s+[a-z]{1,6}";
        SYNTAX_COMMANDS[4] = "^quit";

    }
}
