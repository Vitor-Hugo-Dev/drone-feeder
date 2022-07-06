package com.futureh.dronefeeder.aplication.entrega.controller;

import com.futureh.dronefeeder.aplication.entrega.service.EntregaService;
import com.futureh.dronefeeder.domain.drone.exception.DroneAtivoInexistenteException;
import com.futureh.dronefeeder.domain.entrega.exception.EntregaInvalidaException;
import com.futureh.dronefeeder.domain.entrega.exception.EntregaNaoEncontradaException;
import com.futureh.dronefeeder.domain.entrega.exception.ListaEntregasNaoExisteException;
import com.futureh.dronefeeder.domain.entrega.model.AtualizarEntregaDto;
import com.futureh.dronefeeder.domain.entrega.model.Entrega;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Javadoc.
 */
@RestController
@RequestMapping(value = "/entrega")
public class EntregaController {

  @Autowired
  EntregaService entregaService;

  @GetMapping
  @RequestMapping(
      value = "/test",    
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> testeDeFuncionamento() {
    return ResponseEntity.ok("Hello World!");
  }

  /**
   * metodo para salvar uma entrega.
   */
  @PostMapping
  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Entrega> salvarEntrega(@RequestBody Entrega entrega)
      throws EntregaInvalidaException, 
      DroneAtivoInexistenteException {

    Entrega newEntrega = entregaService.salvarEntrega(entrega);

    return ResponseEntity.status(HttpStatus.OK).body(newEntrega);
  }

  /**
   * Javadoc.
   */
  @GetMapping
  @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Entrega> buscarEntregaPorId(@PathVariable Integer id)
      throws EntregaNaoEncontradaException {
    Entrega entrega = entregaService.buscarEntregaPorId(id);

    return ResponseEntity.status(HttpStatus.OK).body(entrega);
  }

  /**
   * Javadoc.
   */
  @GetMapping
  @RequestMapping(
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Entrega>> retornarTodasEntregas()
      throws ListaEntregasNaoExisteException {
    List<Entrega> entregas = entregaService.retornarTodasEntregas();

    return ResponseEntity.status(HttpStatus.OK).body(entregas);
  }

  /**
   * Javadoc.
   */
  @PostMapping
  @RequestMapping(value = "/{id}",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Entrega> atualizaEntrega(
      @PathVariable Integer id,
      @RequestBody AtualizarEntregaDto novaEntregaDto)
      throws EntregaNaoEncontradaException {

    Entrega entrega = entregaService.atualizaEntrega(id,
        novaEntregaDto.getDataEntrega(),
        novaEntregaDto.getHorarioEntrega());
    return ResponseEntity.status(HttpStatus.OK).body(entrega);
  }

  /**
   * Javadoc.
   */
  @DeleteMapping
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deletarEntrega(@PathVariable Integer id)
      throws EntregaNaoEncontradaException {
    entregaService.deletarEntrega(id);

    return ResponseEntity.ok("Entrega deletada com sucesso");
  }
}
