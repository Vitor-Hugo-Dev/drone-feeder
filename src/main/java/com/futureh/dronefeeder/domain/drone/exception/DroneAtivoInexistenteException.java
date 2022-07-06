package com.futureh.dronefeeder.domain.drone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DroneAtivoInexistenteException extends Exception {
  private static final long serialVersionUID = 1L;

  public DroneAtivoInexistenteException() {
    super("Não existem Drones ativos no momento.");
  }
}
