package com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.drone;

import org.springframework.data.repository.CrudRepository;

import com.futureh.dronefeeder.domain.drone.model.Drone;

public interface DroneRepository extends CrudRepository<Drone, Integer> {

}
