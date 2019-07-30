package de.sybit.machinesimulator.essentials;

public class Unit {

    private String kind;

    private double value = -1;

    public boolean unitIsSetUp() {
        if (value == -1) {
            return false;
        }
        return true;
    }

    public Unit(String kind) {
        this.kind = kind;
    }

    public Unit(String kind, double value) {
        this.kind = kind;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getKind() {
        return kind;
    }
}
