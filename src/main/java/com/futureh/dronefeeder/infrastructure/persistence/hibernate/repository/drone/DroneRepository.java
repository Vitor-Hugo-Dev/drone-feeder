package com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.drone;

import com.futureh.dronefeeder.domain.drone.model.Drone;

import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone, Integer> {

}
