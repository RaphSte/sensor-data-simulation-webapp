CREATE TABLE tsimulationvalues (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	unit_name_key VARCHAR(50) NOT NULL,
	simulation_function VARCHAR(50) NOT NULL,
	interval_lower_bound DOUBLE NOT NULL,
	interval_upper_bound DOUBLE NOT NULL,
	interval_period INT NOT NULL,
	noise_lower_bound DOUBLE NOT NULL,
	noise_upper_bound DOUBLE NOT NULL,
	additional_interval_lower_bound DOUBLE,
	additional_interval_upper_bound DOUBLE,
	additional_interval_period DOUBLE,
	created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



INSERT INTO tsimulationvalues (unit_name_key, simulation_function, interval_lower_bound, interval_upper_bound, interval_period,
                              noise_lower_bound, noise_upper_bound, additional_interval_lower_bound,additional_interval_upper_bound, additional_interval_period, created, updated) VALUES
	('aUnitNameKey', 'Sine',           5 ,15 ,20 ,0  ,0  ,0  ,0  ,0  ,'2019-01-01 15:38:58', '2019-01-01 15:38:58'),
	('anotherUnitNameKey', 'Sine',  5 ,15 ,20 ,0.5  ,0.5  ,15 ,5  ,20 ,'2019-01-01 15:38:58', '2019-01-01 15:38:58');
