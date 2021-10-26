package my.sunway.group5.entity;

/**
 * This class is describing river cell
 */
public class RiverCell {

    /**
     * Any of subtype, like Current or Trap
     */
    private RiverObject riverObject;

    /**
     * Constructor for River Cell
     * @param riverObject any implementation of river object
     */
    public RiverCell(RiverObject riverObject) {
        this.riverObject = riverObject;
    }

    /**
     * Return river object which holding in this river cell
     * @return return river object
     */
    public RiverObject getRiverObject() {
        return riverObject;
    }
}
