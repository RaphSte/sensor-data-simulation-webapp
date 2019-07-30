package de.sybit.machinesimulator.service;


import de.sybit.machinesimulator.essentials.SimulationEssentialsContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class ObjectNameServiceImpl implements ObjectNameService {


    @Autowired
    SimulationEssentialsContainer essentialsContainer;


    @Override
    public List<String> provideSimulationTypes() {
        List<String> availableClasses = new ArrayList<>();
        // create scanner and disable default filters (that is the 'false' argument)
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        // add include filters which matches all the classes (or use your own)
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        // get matching classes defined in the package
        final Set<BeanDefinition> classes = provider.findCandidateComponents("de.sybit.machinesimulator.simulationFunction.impl");
        // this is how you can load the class type from BeanDefinition instance
        for (BeanDefinition bean : classes) {
            try {
                availableClasses.add(Class.forName(bean.getBeanClassName()).getSimpleName());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return availableClasses;
    }


    @Override
    public List<String> provideAvailableSimulations() {
        Set<String> keySet = essentialsContainer.getKeySet();
        List<String> simulationNames = new ArrayList<>();
        for (String key : keySet) {
            simulationNames.add(key);
        }
        return simulationNames;
    }


}