package main;

public class Agent extends GridCell {

    private boolean isAlive = false;
    private int direction;
    private double fwS; // Forward sensor
    private double fwRS; // Forward Right Sensor
    private double fwLS; // Forward Left Sensor

    private final int sensorWidth = 1;
    private final int sensorOffsetDistance = 3; // Offset distance from agent position in pixels
    private final int MIN_DIRECTION = 0;
    private final int MAX_DIRECTION = 7;

    public Agent(boolean isAlive, int direction) {
        this.isAlive = isAlive;
        this.direction = direction;
    }

    public boolean getState() {
        return isAlive;
    }

    public void setState(boolean state) {
        isAlive = state;
    }

    public void setDirection(int newDirection) {
        direction = newDirection;
    }

    public int getDirection() {
        return direction;
    }

    public int getMIN_DIRECTION() {
        return MIN_DIRECTION;
    }

    public int getMAX_DIRECTION() {
        return MAX_DIRECTION;
    }

    public void setFwS(double fwS) {
        this.fwS = fwS;
    }

    public void setFwRS(double fwRS) {
        this.fwRS = fwRS;
    }

    public void setFwLS(double fwLS) {
        this.fwLS = fwLS;
    }

    public double getFwS() {
        return fwS;
    }

    public double getFwRS() {
        return fwRS;
    }

    public double getFwLS() {
        return fwLS;
    }

    public int getSensorOffsetDistance() {
        return sensorOffsetDistance;
    }
}
