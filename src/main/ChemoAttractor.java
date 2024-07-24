package main;

public class ChemoAttractor extends GridCell {

    private final int strengthRadius = 4; // Defines from how many cells can it be detected
    private double strength;
    private final int MIN_STRENGTH = 0;
    private final int MAX_STRENGTH = 10;
    private final double decayFactor = 1.4;

    public ChemoAttractor(double strength) {
        this.strength = strength;
    }

    public int getStrengthRadius() {
        return strengthRadius;
    }

    public int getMAX_STRENGTH() {
        return MAX_STRENGTH;
    }

    public int getMIN_STRENGTH() {
        return MIN_STRENGTH;
    }

    public double getStrength() {
        return strength;
    }
}
