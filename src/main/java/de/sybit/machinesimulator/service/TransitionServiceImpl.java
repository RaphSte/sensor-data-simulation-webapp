package de.sybit.machinesimulator.service;

import de.sybit.machinesimulator.essentials.Interval;
import de.sybit.machinesimulator.essentials.SimulationEssentialsContainer;
import javassist.NotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransitionServiceImpl implements TransitionService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TransitionServiceImpl.class);

    @Autowired
    private SimulationEssentialsContainer essentialsContainer;

    @Override
    public void addTransitionInterval(Interval interval) {
        if (essentialsContainer.transitionIntervalListExistsFor(interval.getKind())) {
            essentialsContainer.addTransitionIntervalToExistingList(interval.getKind(), interval);
        } else {
            essentialsContainer.addTransitionIntervalToNewList(interval.getKind(), interval);
        }
    }

    @Override
    public List<Interval> provideTransitionIntervalsFor(String unitNameKey) {
        try {
            return essentialsContainer.getTransitionIntervalsFor(unitNameKey);
        } catch (NotFoundException e) {
            LOG.warn("No intervals where found. Returning empty list as fallback");
            return new ArrayList<>();
        }
    }

    @Override
    public boolean newTransitionIntervalsAvailableFor(String unitNameKey) {
        return !provideTransitionIntervalsFor(unitNameKey).isEmpty();
    }

}
