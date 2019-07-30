package de.sybit.machinesimulator.simulationFunction;

import java.util.ArrayList;
import java.util.List;

import de.sybit.machinesimulator.essentials.Interval;

@Deprecated
/**
 * @Deprecated
 * Use 'transitionService' instead!
 * */
public class TransitionIntervalProviderService {

    private static List<Interval> intervalContainer = new ArrayList<>();

    public static void addInterval(Interval interval) {
        intervalContainer.add(interval);
    }

    public static List<Interval> provideNewIntervalsFor(String kind) {
        List intervals = new ArrayList();

        for (Interval interval : intervalContainer) {
            if (interval.getKind().equals(kind)) {
                intervals.add(interval);
            }
        }
        return intervals;
    }

    public static boolean newIntervalsAreAvailableFor(String kind) {
        return provideNewIntervalsFor(kind) != null;
    }


}
