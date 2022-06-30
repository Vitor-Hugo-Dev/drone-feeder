package com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.entrega;

import org.springframework.data.repository.CrudRepository;

import com.futureh.dronefeeder.domain.entrega.model.Entrega;

public interface EntregaRepository extends CrudRepository<Entrega, Long> {

}
