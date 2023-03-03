package edu.kit.informatik.application;


import edu.kit.informatik.application.commands.Flow;
import edu.kit.informatik.application.commands.Print;
import edu.kit.informatik.application.commands.add.Add;
import edu.kit.informatik.application.commands.list.List;
import edu.kit.informatik.userinterface.CheckInputLine;
import edu.kit.informatik.userinterface.Output;


/**
 * This class contains the application-method.
 * A method that connects user interface with the program structure
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class Application {

    //utility-class constructor
    private Application() {
    }

    /**
     * Splits the input line into an array, also checks the needed syntax
     * and gives it to the specific method (add, list, print or flow)
     * The methods then return an array or string which is given to the handle output method.
     *
     * @param inputLine input line from the main-method
     * @return true: if program should stop (quit)
     * false: for all other commands
     */
    public static boolean application(String inputLine) {
        String[] splitInputLine = CheckInputLine.checkInputLine(inputLine); //splits and checks (regex)
        String[] output;
        String command = splitInputLine[0];

        switch (command) {
            case "quit":
                return true;
            case "add":
                output = new String[1];
                output[0] = Add.comAdd(splitInputLine);
                break;
            case "list":
                output = List.comList(splitInputLine);
                break;
            case "print":
                output = Print.comPrint(splitInputLine);
                break;
            case "flow":
                output = new String[1];
                output[0] = Flow.comFlow(splitInputLine);
                break;
            default:
                output = new String[1];
                output[0] = splitInputLine[0];
        }
        Output.handleOutput(output);
        return false;
    }
}
