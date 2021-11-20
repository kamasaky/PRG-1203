package my.sunway.group5;

import my.sunway.group5.config.GameConfig;
import my.sunway.group5.entity.Current;
import my.sunway.group5.entity.RiverCell;
import my.sunway.group5.entity.RiverObject;
import my.sunway.group5.entity.Trap;
import my.sunway.group5.io.InputOutput;
import my.sunway.group5.io.TopScoresService;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {

    private final GameConfig gameConfig;
    private final InputOutput inputOutput;
    private final TopScoresService topScoresService;
    private final RiverCell[] river;
    private final Random random;


    private final int minDiceValue = 1;
    private final int maxDiceValue = 6;

    private int firstPlayerPosition = 0;
    private int firstPlayerSteps = 0;
    private int secondPlayerPosition = 0;
    private int secondPlayerSteps = 0;

    private String winnerName;

    public Game(GameConfig gameConfig, InputOutput io) {
        this.gameConfig = gameConfig;
        this.inputOutput = io;
        this.topScoresService = new TopScoresService(gameConfig.getTopScoresFile());
        this.river = new RiverCell[100];
        this.random = new Random();
        for (int index = 0; index < river.length; index++) {
            int rand = random.nextInt(99);
            if (river[rand] != null) {
                continue;
            } else {
                int strength = random.nextInt(3 + 2) - 2;
                river[index] = index % 2 == 0 ? new RiverCell(new Trap(strength)) : new RiverCell(new Current(strength));
            }
        }
    }

    public void start() throws Exception {
        inputOutput.notifyUser("There are top 5 scores of the boat game");
        List<String> topScores = topScoresService.getTopScores().stream().limit(5).collect(Collectors.toList());
        topScores.forEach(System.out::println);
        String firstPlayer = inputOutput.askUser("Enter a name of first player: ");
        String secondPlayer = inputOutput.askUser("Enter a name of second player: ");
        while (!isGameFinished()) {
            printGameState(firstPlayerPosition, firstPlayer, secondPlayerPosition, secondPlayer);

            int step = gitStepWithNotification(firstPlayer);
            firstPlayerPosition += step;
            firstPlayerSteps++;

            step = gitStepWithNotification(secondPlayer);
            secondPlayerPosition += step;
            secondPlayerSteps++;

            firstPlayerPosition = getPlayerPosition(firstPlayer, firstPlayerPosition);
            secondPlayerPosition = getPlayerPosition(secondPlayer, secondPlayerPosition);
        }
        storeScoring();
    }

    private boolean isGameFinished() {
        return firstPlayerPosition == river.length - 1 || secondPlayerPosition == river.length - 1;
    }

    private void printGameState(int playerOnePosition, String playerOneName, int playerTwoPosition, String playerTwoName) {
        StringBuilder buffer = new StringBuilder();
        for (int index = 0; index < river.length; index++) {
            if (index == 0 && playerOnePosition == 0 && playerTwoPosition == 0) {
                buffer.append("[")
                        .append(playerOneName)
                        .append(", ")
                        .append(playerTwoName).append("]");
            } else if (index == playerOnePosition && index == playerTwoPosition) {
                buffer.append("[")
                        .append(playerOneName)
                        .append(", ")
                        .append(playerTwoName).append("]");
            } else if (index == playerOnePosition) {
                buffer.append("[")
                        .append(playerOneName)
                        .append("]");
            } else if (index == playerTwoPosition) {
                buffer.append("[")
                        .append(playerTwoName)
                        .append("]");
            } else {
                buffer.append("[ ]");
            }
        }
        inputOutput.notifyUser(buffer.toString());
    }

    private int gitStepWithNotification(String playerName) {
        int step = random.nextInt(maxDiceValue - minDiceValue) + minDiceValue;
        inputOutput.notifyUser("It is " + playerName + "\'s turn and he/she got " + step);
        return step;
    }

    private int getPlayerPosition(String playerName, int playerPosition) {
        if (playerPosition > river.length - 1) {
            this.winnerName = playerName;
            return river.length - 1;
        }
        RiverCell currentPlayerCell = river[playerPosition];
        if (currentPlayerCell != null && currentPlayerCell.getRiverObject() != null) {
            RiverObject riverObject = currentPlayerCell.getRiverObject();
            if (riverObject instanceof Current) {
                Current current = (Current) riverObject;
                inputOutput.notifyUser(playerName + " was caught by current with strength = " + current.getStrength());
                return playerPosition + current.getStrength();
            } else if (riverObject instanceof Trap) {
                Trap trap = (Trap) riverObject;
                inputOutput.notifyUser(playerName + " was caught by trap with strength = " + trap.getStrength());
                return playerPosition + trap.getStrength();
            } else {
                return playerPosition;
            }
        }
        return playerPosition;
    }

    private void storeScoring() throws Exception {
        String score = "\n" + winnerName + " " + Math.min(firstPlayerSteps, secondPlayerSteps);
        Files.write(Paths.get(gameConfig.getTopScoresFile()), score.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }
}
