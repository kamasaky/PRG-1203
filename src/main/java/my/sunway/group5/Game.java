package my.sunway.group5;

import my.sunway.group5.config.GameConfig;
import my.sunway.group5.io.InputOutput;
import my.sunway.group5.io.TopScoresService;

import java.util.List;

public class Game {

    private final GameConfig gameConfig;
    private final InputOutput inputOutput;
    private final TopScoresService topScoresService;

    public Game(GameConfig gameConfig, InputOutput io) {
        this.gameConfig = gameConfig;
        this.inputOutput = io;
        this.topScoresService = new TopScoresService(gameConfig.getTopScoresFile());
    }

    public void start() {
        inputOutput.notifyUser("There are top 5 scores of the boat game");
        List<String> topScores = topScoresService.getTopScores();
        topScores.forEach(System.out::println);
    }
}
