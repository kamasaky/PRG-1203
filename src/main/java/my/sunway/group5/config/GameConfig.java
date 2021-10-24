package my.sunway.group5.config;

/**
 * It is class for holding all configuration for the game
 * @version 0.1
 * @author voronyansky(19102631)
 */
public class GameConfig {

    /**
     * Path where stores config file
     */
    private String configurationFilePath;

    /**
     * Path where stores top score file
     */
    private String topScoresFile;


    /**
     * It is constructor for configuration class
     * @param configurationFilePath - path to config file
     * @param topScoresFile path to top score file
     */
    public GameConfig(String configurationFilePath, String topScoresFile) {
        this.configurationFilePath = configurationFilePath;
        this.topScoresFile = topScoresFile;
    }

    /**
     * Return path to configuration file
     * @return string represents paths to file
     */
    public String getConfigurationFilePath() {
        return configurationFilePath;
    }

    /**
     * Set new path for configuration file
     * @param configurationFilePath - path to configuration file
     */
    public void setConfigurationFilePath(String configurationFilePath) {
        this.configurationFilePath = configurationFilePath;
    }

    /**
     * Return path to top scores file
     * @return string represents path for top score file
     */
    public String getTopScoresFile() {
        return topScoresFile;
    }

    /**
     * Set new top score file path
     * @param topScoresFile new path for top score file
     */
    public void setTopScoresFile(String topScoresFile) {
        this.topScoresFile = topScoresFile;
    }
}
