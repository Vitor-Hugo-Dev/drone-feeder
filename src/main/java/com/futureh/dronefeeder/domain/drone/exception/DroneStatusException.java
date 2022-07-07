package com.futureh.dronefeeder.domain.drone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DroneStatusException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DroneStatusException() {
    super("Status inválido, só pode ser 'Ativo' ou 'Ocupado'.");
  }
}
