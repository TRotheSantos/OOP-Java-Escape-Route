package edu.kit.informatik.userinterface;

import java.util.regex.Pattern;


/**
 * This class contains a method to check and split the input line.
 * For splitting the Pattern class is imported.
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class CheckInputLine {

    // utility-class constructor
    private CheckInputLine() {
    }


    /**
     * Method to first check the input line with the given command patterns and then splits the arguments into an array
     *
     * @param inputLine the input line (read line)
     * @return an checked array of Strings
     */
    public static String[] checkInputLine(String inputLine) {

        boolean isInputCorrect = false;
        String[] splitInputLine = new String[1];

        for (String lookFor : CommandPattern.SYNTAX_COMMANDS) {
            isInputCorrect = isInputCorrect || Pattern.matches(lookFor, inputLine);
        }
        if (isInputCorrect) {
            splitInputLine = inputLine.split("[; ]");
        } else {
            splitInputLine[0] = "Error, incorrect command or syntax.";

        }
        return splitInputLine;
    }
}
