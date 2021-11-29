package my.sunway.group5.entity;

/**
 * It is class represent player of the game
 */
public class Player {

    /**
     * Name of the player
     */
    private String name;

    /**
     * Score of the player
     */
    private int score;

    /**
     * Position of player
     */
    private int position;

    /**
     * Numbers of step
     */
    private int steps;

    /**
     * Constructor with name and score
     * @param name of the player
     * @param score of the player
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.position = 0;
        this.steps = 0;
    }

    /**
     * Constructor with name
     * @param name of the player
     */
    public Player(String name) {
        this(name, 0);
    }

    /**
     * Return name of the player
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of the player
     * @param name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the score of this player
     * @return score of this player
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score of the player
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get position of the player
     * @return position of the player
     */
    public int getPosition() {
        return position;
    }

    /**
     * Return position of the player
     * @param position of the player
     */
    public void setPosition(int position) {
        this.position = Math.max(position, 0);
    }

    /**
     * Return number of steps
     * @return number of steps
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Set number of steps
     * @param steps
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }
}
