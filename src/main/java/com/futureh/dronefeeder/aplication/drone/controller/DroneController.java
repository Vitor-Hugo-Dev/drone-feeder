package com.futureh.dronefeeder.aplication.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.futureh.dronefeeder.aplication.drone.service.DroneService;
import com.futureh.dronefeeder.domain.drone.model.Drone;

@RestController
public class DroneController {
  @Autowired
  private DroneService droneService;
  
  /**
   * Salva novo Drone (POST).
   */
  @RequestMapping(
    value = "/drone",
    method = RequestMethod.POST)
  public ResponseEntity<Drone> salvaDrone(@RequestBody Drone novoDrone) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(droneService.salvaDrone(novoDrone));
  }
  
  /**
   * Retorna Drone existente pelo id (GET).
   */
  @RequestMapping(
    value = "/drone/{droneId}",
    method = RequestMethod.GET)
  public ResponseEntity<Drone> retornaDronePeloId(@PathVariable Integer droneId) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(droneService.retornaDronePeloId(droneId));
  }
  
  /**
   * Retorna Todos os drones (GET).
   */
  @RequestMapping(
     value = "/drone",
     method = RequestMethod.GET)
   public ResponseEntity<Iterable<Drone>> retornaTodosDrones() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(droneService.retornaTodosDrones());
  }
  
  /**
   * Atualiza Status Drone (POST).
   */
   @RequestMapping(
       value = "/drone/{droneId}/status/{statusDrone}",
       method = RequestMethod.POST)
   public ResponseEntity<Drone> atualizaStatusDrone(@PathVariable Integer droneId, @PathVariable String statusDrone) {
     return ResponseEntity
         .status(HttpStatus.OK)
         .body(droneService.atualizaStatusDrone(droneId, statusDrone));
   }
  
   /**
    * Atualiza Status Drone (POST).
    */
    @RequestMapping(
        value = "/drone/{droneId}/localizacao/{localizacaoDrone}",
        method = RequestMethod.POST)
    public ResponseEntity<Drone> atualizaLocalizacaoDrone(@PathVariable Integer droneId, @PathVariable String localizacaoDrone) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(droneService.atualizaLocalizacaoDrone(droneId, localizacaoDrone));
    }
    
    /**
     * Deleta drone (DELETE).
     */
    @RequestMapping(
        value = "/series/{droneId}",
        method = RequestMethod.DELETE)
    public ResponseEntity<String> deletaDrone(@PathVariable Integer droneId) {
      return ResponseEntity.status(HttpStatus.OK).body(droneService.deletaDrone(droneId));
    }
}
