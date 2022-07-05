package com.futureh.dronefeeder.domain.applicationerror;

import org.springframework.http.HttpStatus;

public class ApplicationError {
  private String error;
  private HttpStatus status;

  public ApplicationError(String error, HttpStatus status) {
    this.error = error;
    this.status = status;
  }

  public String getError() {
    return this.error;
  }

  public HttpStatus getStatus() {
    return this.status;
  }

}
