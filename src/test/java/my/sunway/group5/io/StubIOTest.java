package my.sunway.group5.io;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StubIOTest {

    @Test
    public void whenProgramAskUserShouldGetCorrectInput() {
        List<String> input = Arrays.asList("Andrew");
        List<String> outputs = Arrays.asList("Enter your name please: ");
        StubIO stubIO = new StubIO(input, outputs);
        List<String> actualInput = stubIO.getInput();
        List<String> actualOutput = stubIO.getOutput();
        assertEquals(input.get(0), actualInput.get(0));
        assertEquals(outputs.get(0), actualOutput.get(0));
    }

    @Test
    public void whenProgramAskUserForNumberShouldGetCorrectNumber() {
        List<String> input = Arrays.asList("123");
        List<String> output = Arrays.asList("Enter some number please");
        StubIO stubIO = new StubIO(input, output);
        List<String> actualInput = stubIO.getInput();
        List<String> actualOutput = stubIO.getOutput();
        assertEquals(input.get(0), actualInput.get(0));
        assertEquals(output.get(0), actualOutput.get(0));
    }

    @Test
    public void whenProgramNotifyUserShouldCheckThatMessageDelivered() {
        List<String> output = Arrays.asList("Hello", "Welcome to the Boat Game", "Test");
        StubIO stubIO = new StubIO(new ArrayList<>(), output);
        assertEquals(stubIO.getOutput(), output);
    }
}