package main;

public class ChemoAttractor extends GridCell {

    private final double strengthRadius = 4; // Defines from how many cells can it be detected
    private double strength;
    private final int MIN_STRENGTH = 0;
    private final int MAX_STRENGTH = 1;
    private final double decayFactor = 1.4;

    public ChemoAttractor() {
    }
}
