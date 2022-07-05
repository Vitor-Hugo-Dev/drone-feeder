package com.futureh.dronefeeder.domain.entrega.exception;

import com.futureh.dronefeeder.domain.applicationerror.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntregaExceptionHandler {

  /**
   * retorna erro personalizado.
   */
  @ExceptionHandler(EntregaInvalidaException.class)
  public ResponseEntity<ApplicationError> entregaInvalida(EntregaInvalidaException error) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ApplicationError(error.getMessage(), HttpStatus.BAD_REQUEST));
  }
}
