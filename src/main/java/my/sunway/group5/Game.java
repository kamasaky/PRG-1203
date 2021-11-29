package my.sunway.group5;

import my.sunway.group5.config.GameConfig;
import my.sunway.group5.entity.*;
import my.sunway.group5.io.InputOutput;
import my.sunway.group5.io.TopScoresService;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {

    private final GameConfig gameConfig;
    private final InputOutput inputOutput;
    private final TopScoresService topScoresService;
    private final Random random;

    private String winnerName;

    private List<Player> players = new ArrayList<>();
    private Player winner = null;
    private RiverCell[] river;
    private Dice dice;

    public Game(GameConfig gameConfig, InputOutput io) {
        this.gameConfig = gameConfig;
        this.inputOutput = io;
        this.topScoresService = new TopScoresService(gameConfig.getTopScoresFile());
        this.random = new Random();
    }

    public void start() throws Exception {
        inputOutput.notifyUser("There are top 5 scores of the boat game");
        List<String> topScores = topScoresService.getTopScores().stream().limit(5).collect(Collectors.toList());
        topScores.forEach(System.out::println);
        int howManyPlayers = inputOutput.askUserForNumber("Enter a number of players: ");
        for (int index = 0; index < howManyPlayers; index++) {
            String nameOfPlayer = inputOutput.askUser("Enter a name of player: ");
            players.add(new Player(nameOfPlayer));
        }
        int minDiceValue = inputOutput.askUserForNumber("Enter a min value of dice: ");
        int maxDiceValue = inputOutput.askUserForNumber("Enter a max value of dice: ");
        this.dice = new Dice(minDiceValue, maxDiceValue);

        int minRiverLength = inputOutput.askUserForNumber("Enter a min value of river: ");
        int maxRiverLength = inputOutput.askUserForNumber("Enter a max value of river: ");
        this.initRiver(minRiverLength, maxRiverLength);

        while (!isGameFinished()) {
            printGameState(players);

            for (Player player : players) {
                int playerStep = gitStepWithNotification(player);
                int playerPosition = getPlayerPosition(player, playerStep);
                player.setPosition(player.getPosition() + playerPosition);
                player.setSteps(player.getSteps() + 1);
            }
        }
        printGameState(players);
        printUnmaskedRiver();
        storeScoring(this.winner);
    }

    private boolean isGameFinished() {
        for (Player player : players) {
            if (player.getPosition() >= river.length - 1) {
                this.winner = player;
                inputOutput.notifyUser("Winner is " + player.getName());
                return true;
            }
        }
        return false;
    }

    private void printGameState(List<Player> players) {
        StringBuilder buffer = new StringBuilder();
        for (int index = 0; index < river.length; index++) {
            for (Player player : players) {
                if (index == player.getPosition()) {
                    buffer.append("[").append(player.getName()).append("]");
                } else {
                    buffer.append("[").append(" ").append("]");
                }
            }
        }
        inputOutput.notifyUser(buffer.toString());
    }


    private int gitStepWithNotification(Player player) {
        int step = random.nextInt(dice.getMax() - dice.getMin()) + dice.getMin();
        inputOutput.notifyUser("It is " + player.getName() + "\'s turn and he/she got " + step);
        return step;
    }

    private int getPlayerPosition(Player player, int step) {
        if (player.getPosition() > river.length - 1) {
            this.winnerName = player.getName();
            return river.length - 1;
        }
        int nextCell = Math.min(player.getPosition() + step, river.length - 1);
        RiverCell currentPlayerCell = river[nextCell];
        if (currentPlayerCell != null && currentPlayerCell.getRiverObject() != null) {
            RiverObject riverObject = currentPlayerCell.getRiverObject();
            if (riverObject instanceof Current) {
                Current current = (Current) riverObject;
                inputOutput.notifyUser(player.getName() + " was caught by current with strength = " + current.getStrength());
                current.setHasVisited(true);
                current.setVisitedBy(player.getName());
                return player.getPosition() + current.getStrength();
            } else if (riverObject instanceof Trap) {
                Trap trap = (Trap) riverObject;
                inputOutput.notifyUser(player.getName() + " was caught by trap with strength = " + trap.getStrength());
                trap.setVisitedBy(player.getName());
                trap.setHasVisited(true);
                return player.getPosition() + trap.getStrength();
            } else {
                return player.getPosition() + step;
            }
        }
        return player.getPosition() + step;
    }

    private void storeScoring(Player player) throws Exception {
        String score = "\n" + winnerName + " " + player.getSteps();
        Files.write(Paths.get(gameConfig.getTopScoresFile()), score.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }

    private void initRiver(int minRiverLength, int maxRiverLength) {
        this.river = new RiverCell[maxRiverLength - 1];
        for (int index = 0; index < river.length; index++) {
            int rand = random.nextInt(maxRiverLength - 1 - minRiverLength) + minRiverLength;
            if (river[rand] != null) {
                continue;
            } else {
                int strength = random.nextInt(3 + 2) - 2;
                river[index] = index % 2 == 0 ? new RiverCell(new Trap(strength)) : new RiverCell(new Current(strength));
            }
        }
    }

    private void printUnmaskedRiver() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < this.river.length; index++) {
            RiverCell riverCell = this.river[index];
            if (riverCell != null) {
                RiverObject riverObject = riverCell.getRiverObject();
                if (riverObject == null) {
                    stringBuilder.append("[").append(" ").append("]");
                } else if (riverObject instanceof Trap) {
                    Trap trap = (Trap) riverObject;
                    if (trap.isHasVisited()) {
                        stringBuilder.append("[").append("T has visited by ").append(trap.getVisitedBy()).append("]");
                    } else {
                        stringBuilder.append("[").append("T").append("]");
                    }
                } else if (riverObject instanceof Current) {
                    Current trap = (Current) riverObject;
                    if (trap.isHasVisited()) {
                        stringBuilder.append("[").append("C has visited by ").append(trap.getVisitedBy()).append("]");
                    } else {
                        stringBuilder.append("[").append("C").append("]");
                    }
                }
            } else {
                stringBuilder.append("[").append(" ").append("]");
            }
        }
        inputOutput.notifyUser(stringBuilder.toString());
    }
}
