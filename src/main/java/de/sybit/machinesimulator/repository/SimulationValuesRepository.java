package de.sybit.machinesimulator.repository;

import de.sybit.machinesimulator.entities.SimulationValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimulationValuesRepository extends JpaRepository<SimulationValues, Long> {
    @Query("SELECT s FROM SimulationValues s WHERE s.unit_name_key = ?1")
    public Optional<SimulationValues> getSimulationValuesByUnit_name_key(String unit_name_key);

}
