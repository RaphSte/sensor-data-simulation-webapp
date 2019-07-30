package de.sybit.machinesimulator.service;

import de.sybit.machinesimulator.essentials.Interval;

import java.util.List;

public interface TransitionService {
    void addTransitionInterval(Interval interval);

    List<Interval> provideTransitionIntervalsFor(String unitNameKey);

    boolean newTransitionIntervalsAvailableFor(String kind);
}
