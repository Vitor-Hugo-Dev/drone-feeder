package com.futureh.dronefeeder.aplication.entrega.controller;

import com.futureh.dronefeeder.aplication.entrega.service.EntregaService;
import com.futureh.dronefeeder.domain.drone.model.Drone;
import com.futureh.dronefeeder.domain.entrega.exception.EntregaInvalidaException;
import com.futureh.dronefeeder.domain.entrega.model.Entrega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/entrega")
public class EntregaController {

  @Autowired
  EntregaService entregaService;


  @GetMapping
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> testeDeFuncionamento() {
    return ResponseEntity.ok("Hello World!");
  }

  /**
   * metodo para salvar uma entrega.
   */
  @PostMapping
  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
      )
  public ResponseEntity<Entrega> salvarEntrega(@RequestBody Entrega entrega)
      throws EntregaInvalidaException {
    Entrega editEntrega = entrega;
    editEntrega.setDrone(new Drone());
    Entrega newEntrega = entregaService.salvarEntrega(entrega);

    return ResponseEntity.ok(newEntrega);

  }
}
