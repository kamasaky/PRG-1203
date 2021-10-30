package my.sunway.group5.io;

import java.util.List;

public class StubIO implements InputOutput {

    private List<String> input;
    private List<String> output;

    public StubIO(List<String> input, List<String> output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public String askUser(String prompt) {
        output.add(prompt);
        return input.get(input.size() - 1);
    }

    @Override
    public int askUserForNumber(String prompt) {
        output.add(prompt);
        return Integer.parseInt(input.get(input.size() - 1));
    }

    @Override
    public void notifyUser(String notification) {
        output.add(notification);
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }
}
