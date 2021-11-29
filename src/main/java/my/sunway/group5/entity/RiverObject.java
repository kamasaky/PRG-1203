package my.sunway.group5.entity;


/**
 * This is basic class for Current and Trap classes
 * @see Current
 * @see Trap
 */
public abstract class RiverObject {

    /**
     * Strength of the river object
     */
    protected int strength;

    /**
     * Name of object.
     */
    protected String name;

    /**
     * Flag that this cell has visited by any player
     */
    protected boolean hasVisited;

    /**
     * Name of player which visited cell
     */
    protected String visitedBy;

    /**
     * Constructor for creating new RiverObject
     * @param strength - strength of the river object
     */
    public RiverObject(int strength) {
        this.strength = strength;
    }

    /**
     *
     * @param strength
     * @param name
     */
    public RiverObject(int strength, String name) {
        this.strength = strength;
        this.name = name;
    }

    /**
     * Return strength of the river object
     * @return int which represents strength of the river object
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Return name of river object
     * @return name of river object
     */
    public String getName() {
        return name;
    }
}
