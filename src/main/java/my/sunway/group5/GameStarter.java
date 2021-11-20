package my.sunway.group5;

import my.sunway.group5.config.GameConfig;
import my.sunway.group5.io.ConsoleIO;
import my.sunway.group5.io.InputOutput;

public class GameStarter {

    public static void main(String[] args) throws Exception {
        GameConfig gameConfig = new GameConfig("", "./TopScores.txt");
        InputOutput inputOutput = new ConsoleIO();
        new Game(gameConfig, inputOutput).start();
    }
}
