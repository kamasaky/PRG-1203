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
     * Constructor for creating new RiverObject
     * @param strength - strength of the river object
     */
    public RiverObject(int strength) {
        this.strength = strength;
    }

    /**
     * Return strength of the river object
     * @return int which represents strength of the river object
     */
    public int getStrength() {
        return strength;
    }
}
