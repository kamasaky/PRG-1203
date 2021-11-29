package my.sunway.group5.io;

import java.util.List;

/**
 * This is stub input output implementation
 */
public class StubIO implements InputOutput {

    /**
     * Expected input
     */
    private List<String> input;

    /**
     * Expected output
     */
    private List<String> output;

    /**
     * Constructor for this class
     * @param input list of expected input
     * @param output list of expected output
     */
    public StubIO(List<String> input, List<String> output) {
        this.input = input;
        this.output = output;
    }

    /**
     * Add string to output list and ask user to enter some information
     * @param prompt string for prompt user
     * @return enter by user string
     */
    @Override
    public String askUser(String prompt) {
        output.add(prompt);
        return input.get(input.size() - 1);
    }

    /**
     * Add string to output list and ask user for number
     * @param prompt - string prompt for user
     * @return number from user
     */
    @Override
    public int askUserForNumber(String prompt) {
        output.add(prompt);
        return Integer.parseInt(input.get(input.size() - 1));
    }

    /**
     * Add string to output list
     * @param notification - string which will show to user
     */
    @Override
    public void notifyUser(String notification) {
        output.add(notification);
    }

    /**
     * Get all inputs
     * @return list of inputs
     */
    public List<String> getInput() {
        return input;
    }

    /**
     * Set list of inputs
     * @param input list of strings
     */
    public void setInput(List<String> input) {
        this.input = input;
    }

    /**
     * Get list of outputs
     * @return list of outputs
     */
    public List<String> getOutput() {
        return output;
    }

    /**
     * Set list of output
     * @param output list of all outputs
     */
    public void setOutput(List<String> output) {
        this.output = output;
    }
}
