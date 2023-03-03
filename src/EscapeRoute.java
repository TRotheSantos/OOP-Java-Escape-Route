import edu.kit.informatik.Terminal;
import edu.kit.informatik.application.Application;
import edu.kit.informatik.data.Data;

/**
 * Class containing the main-method
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class EscapeRoute {

    // utility-class constructor
    private EscapeRoute() {
    }

    /**
     * Main to run the program, the terminal input goes to to the application method.
     * Also data is initialized (an array with the regex specifications)
     *
     * @param args the command-line arguments, not used
     */
    public static void main(String[] args) {
        Data.initData();
        boolean programStop = false;
        int i = 0;
        while (!programStop) {
            String inputLine = Terminal.readLine();
            programStop = Application.application(inputLine);
        }
    }

}



