package de.sybit.machinesimulator.essentials;

public class Noise {

    private String kind;

    private double lowerNoiseBound;

    private double upperNoiseBound;

    public double getNoise() {
        return (Math.random() * getAbsNoiseBounds() - Math.abs(lowerNoiseBound));
    }

    public Noise(String kind, double lowerNoiseBound, double upperNoiseBound) {
        this.kind = kind;
        this.lowerNoiseBound = lowerNoiseBound;
        this.upperNoiseBound = upperNoiseBound;
    }

    private double getAbsNoiseBounds() {
        return Math.abs(lowerNoiseBound) + Math.abs(upperNoiseBound);
    }

    public void setNoiseBounds(double lowerNoiseBound, double upperNoiseBound) {
        this.lowerNoiseBound = lowerNoiseBound;
        this.upperNoiseBound = upperNoiseBound;
    }

    public void setNoiseBounds(double noiseBounds) {
        this.lowerNoiseBound = noiseBounds;
        this.upperNoiseBound = noiseBounds * -1;
    }

    public String getKind() {
        return kind;
    }

    public double getLowerNoiseBound() {
        return lowerNoiseBound;
    }

    public double getUpperNoiseBound() {
        return upperNoiseBound;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setLowerNoiseBound(double lowerNoiseBound) {
        this.lowerNoiseBound = lowerNoiseBound;
    }

    public void setUpperNoiseBound(double upperNoiseBound) {
        this.upperNoiseBound = upperNoiseBound;
    }


}
