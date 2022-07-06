package com.futureh.dronefeeder.domain.entrega.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntregaNaoEncontradaException extends Exception {

  public EntregaNaoEncontradaException() {
    super("Entrega não encontrada!");
  }
  
}
