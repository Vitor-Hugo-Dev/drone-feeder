package com.futureh.dronefeeder.domain.entrega.model;

public class AtualizarEntregaDto {

  private String dataEntrega;

  private String horarioEntrega;

  public AtualizarEntregaDto(String dataEntrega, String horarioEntrega) {
    this.dataEntrega = dataEntrega;
    this.horarioEntrega = horarioEntrega;
  }

  public String getDataEntrega() {
    return this.dataEntrega;
  }

  public String getHorarioEntrega() {
    return this.horarioEntrega;
  }

}
