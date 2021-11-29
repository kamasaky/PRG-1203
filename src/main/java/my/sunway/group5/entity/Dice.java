package my.sunway.group5.entity;

public class Dice {

    private final int min;
    private final int max;

    public Dice(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
