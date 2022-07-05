package com.futureh.dronefeeder.domain.drone.exception;

import com.futureh.dronefeeder.domain.applicationerror.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DroneExceptionsHandler {
  /**
   * Retorna erro de dados inseridos Drone.
   */
  @ExceptionHandler(DroneInvalidoException.class)
  public ResponseEntity<ApplicationError> droneInvalido(DroneInvalidoException error) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ApplicationError(error.getMessage(), HttpStatus.BAD_REQUEST));
  }

  /**
   * Retorna erro.
   */
  @ExceptionHandler(DroneInexistenteException.class)
  public ResponseEntity<ApplicationError> droneInexistente(DroneInexistenteException error) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApplicationError(error.getMessage(), HttpStatus.NOT_FOUND));
  }
}
