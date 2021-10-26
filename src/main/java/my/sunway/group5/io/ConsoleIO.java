package my.sunway.group5.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * It is implementation of input and output interfaces
 * This classes provide functionalities for get input from user
 * and send message to user
 * @author voronyansky(19102631)
 */
public class ConsoleIO implements InputOutput {

    /**
     * Instance of BufferedReader for reading input from console
     */
    private BufferedReader bufferedReader;

    /**
     * Constructor. Only assign real buffered reader to variable above
     */
    public ConsoleIO() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Print message for user at the console, after waiting for input.
     * Input cannot be null or empty string, if so method will prompt user again
     * @param prompt - message for user about what he/she should do
     * @return - not null and not empty string from user
     */
    @Override
    public String askUser(String prompt) {
        System.out.println(prompt);
        boolean isValidInput = false;
        String value = "";
        while (!isValidInput) {
            try {
                value = bufferedReader.readLine();
                if (value != null && !"".equals(value)) {
                    isValidInput = true;
                }
            } catch (IOException ioe) {
                System.out.println("Sorry, I don't understand this");
            }
        }
        return value;
    }

    /**
     * Prompt user to input a number. If input is not a number, then ask again
     * Can return any integers such as positive, or negative as well
     * @param prompt - detailed message about what number program expect
     * @return integer from user
     */
    @Override
    public int askUserForNumber(String prompt) {
        System.out.println(prompt);
        boolean isValidInput = false;
        int value = -1;
        while (!isValidInput) {
            try {
                String input = bufferedReader.readLine();
                if (input != null && !"".equals(input)) {
                    value = Integer.parseInt(input);
                }
            } catch (IOException ioe) {
                System.out.println("Sorry, I don't understand this");
            } catch (NumberFormatException nfe) {
                System.out.println("Sorry, but you entered not a number");
            }
        }
        return value;
    }

    /**
     * Prompt user some information
     * @param notification - any string
     */
    @Override
    public void notifyUser(String notification) {
        System.out.println(notification);
    }
}
