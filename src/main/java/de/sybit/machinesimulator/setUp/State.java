package de.sybit.machinesimulator.setUp;

public class State {

    private static boolean simulationRunning = false;

    public static void stopSimulaton() {
        State.simulationRunning = false;
    }

    public static void startSimulaton() {
        State.simulationRunning = true;
    }

    public static boolean isSimulationRunning() {
        return simulationRunning;
    }

}
