package de.sybit.machinesimulator.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tsimulationvalues")
public class SimulationValues extends AbstractModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "unit_name_key", nullable = false)
    @Size(max = 255)
    private String unit_name_key;

    @Column(name = "simulation_function", nullable = false)
    @Size(max = 255)
    private String simulation_function;

    @Column(name = "interval_lower_bound", nullable = false)
    private double interval_lower_bound;

    @Column(name = "interval_upper_bound", nullable = false)
    private double interval_upper_bound;

    @Column(name = "interval_period", nullable = false)
    private int interval_period;

    @Column(name = "noise_lower_bound", nullable = false)
    private double noise_lower_bound;

    @Column(name = "noise_upper_bound", nullable = false)
    private double noise_upper_bound;

    @Column(name = "additional_interval_lower_bound", nullable = true)
    private double additional_interval_lower_bound;

    @Column(name = "additional_interval_upper_bound", nullable = true)
    private double additional_interval_upper_bound;

    @Column(name = "additional_interval_period", nullable = true)
    private double additional_interval_period;

    public SimulationValues(@Size(max = 255) String unit_name_key, @Size(max = 255) String simulation_function,
                            double interval_lower_bound, double interval_upper_bound, int interval_period, double noise_lower_bound,
                            double noise_upper_bound) {
        this.unit_name_key = unit_name_key;
        this.simulation_function = simulation_function;
        this.interval_lower_bound = interval_lower_bound;
        this.interval_upper_bound = interval_upper_bound;
        this.interval_period = interval_period;
        this.noise_lower_bound = noise_lower_bound;
        this.noise_upper_bound = noise_upper_bound;
    }

    public SimulationValues(@Size(max = 255) String unit_name_key, @Size(max = 255) String simulation_function, double interval_lower_bound,
                            double interval_upper_bound, int interval_period, double noise_lower_bound, double noise_upper_bound,
                            double additional_interval_lower_bound, double additional_interval_upper_bound, double additional_interval_period) {
        this.unit_name_key = unit_name_key;
        this.simulation_function = simulation_function;
        this.interval_lower_bound = interval_lower_bound;
        this.interval_upper_bound = interval_upper_bound;
        this.interval_period = interval_period;
        this.noise_lower_bound = noise_lower_bound;
        this.noise_upper_bound = noise_upper_bound;
        this.additional_interval_lower_bound = additional_interval_lower_bound;
        this.additional_interval_upper_bound = additional_interval_upper_bound;
        this.additional_interval_period = additional_interval_period;
    }

    public SimulationValues() {
        //default constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnit_name_key() {
        return unit_name_key;
    }

    public void setUnit_name_key(String unit_name_key) {
        this.unit_name_key = unit_name_key;
    }

    public String getSimulation_function() {
        return simulation_function;
    }

    public void setSimulation_function(String simulation_function) {
        this.simulation_function = simulation_function;
    }

    public double getInterval_lower_bound() {
        return interval_lower_bound;
    }

    public void setInterval_lower_bound(double interval_lower_bound) {
        this.interval_lower_bound = interval_lower_bound;
    }

    public double getInterval_upper_bound() {
        return interval_upper_bound;
    }

    public void setInterval_upper_bound(double interval_upper_bound) {
        this.interval_upper_bound = interval_upper_bound;
    }

    public int getInterval_period() {
        return interval_period;
    }

    public void setInterval_period(int interval_period) {
        this.interval_period = interval_period;
    }

    public double getNoise_lower_bound() {
        return noise_lower_bound;
    }

    public void setNoise_lower_bound(double noise_lower_bound) {
        this.noise_lower_bound = noise_lower_bound;
    }

    public double getNoise_upper_bound() {
        return noise_upper_bound;
    }

    public void setNoise_upper_bound(double noise_upper_bound) {
        this.noise_upper_bound = noise_upper_bound;
    }

    public double getAdditional_interval_lower_bound() {
        return additional_interval_lower_bound;
    }

    public void setAdditional_interval_lower_bound(double additional_interval_lower_bound) {
        this.additional_interval_lower_bound = additional_interval_lower_bound;
    }

    public double getAdditional_interval_upper_bound() {
        return additional_interval_upper_bound;
    }

    public void setAdditional_interval_upper_bound(double additional_interval_upper_bound) {
        this.additional_interval_upper_bound = additional_interval_upper_bound;
    }

    public double getAdditional_interval_period() {
        return additional_interval_period;
    }

    public void setAdditional_interval_period(double additional_interval_period) {
        this.additional_interval_period = additional_interval_period;
    }

    @Override
    public String toString() {
        return "SimulationValues{" +
                "id=" + id +
                ", unit_name_key='" + unit_name_key + '\'' +
                ", simulation_function='" + simulation_function + '\'' +
                ", interval_lower_bound=" + interval_lower_bound +
                ", interval_upper_bound=" + interval_upper_bound +
                ", interval_period=" + interval_period +
                ", noise_lower_bound=" + noise_lower_bound +
                ", noise_upper_bound=" + noise_upper_bound +
                ", additional_interval_lower_bound=" + additional_interval_lower_bound +
                ", additional_interval_upper_bound=" + additional_interval_upper_bound +
                ", additional_interval_period=" + additional_interval_period +
                '}';
    }


}
