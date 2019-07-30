package de.sybit.machinesimulator.essentials;

public class Interval {

    private String kind;

    private double startingValue;

    private double targetValue;

    private int timeFrame;

    public Interval(String kind) {
        this.kind = kind;
    }

    public Interval(String kind, double startingValue, double targetValue, int timeFrame) {
        this.kind = kind;
        this.startingValue = startingValue;
        this.targetValue = targetValue;
        this.timeFrame = timeFrame;
    }

    public void setBounds(double lowerBound, double upperBound) {
        this.startingValue = lowerBound;
        this.targetValue = upperBound;
    }

    public void setBounds(double noiseBounds) {
        this.startingValue = noiseBounds;
        this.targetValue = noiseBounds * -1;
    }

    public String getKind() {
        return kind;
    }

    public double getStartingValue() {
        return startingValue;
    }

    public void setStartingValue(double startingValue) {
        this.startingValue = startingValue;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }
}
