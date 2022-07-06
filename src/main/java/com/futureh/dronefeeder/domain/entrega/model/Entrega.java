package com.futureh.dronefeeder.domain.entrega.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.futureh.dronefeeder.domain.drone.model.Drone;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_entregas")
// https://pt.stackoverflow.com/questions/242288
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Entrega {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "drone_id")
  private Drone drone;

  private String statusPedido;

  private String nomeCliente;

  private String enderecoEntrega;

  private String idVideo;

  private LocalDate dataChegadaPedido;

  private LocalDate dataEntregaPedido;

  private LocalTime horarioRecebimentoPedido;

  private LocalTime horarioEntregaPedido;

  /**
   * construtor vazio.
   */
  public Entrega() {

  }

  /**
   * construtor com todos os atribudos incluindo o ID.
   * 
   */
  public Entrega(Integer id,
      Drone drone,
      String statusPedido,
      String nomeCliente,
      String enderecoEntrega,
      String idVideo,
      LocalDate dataChegadaPedido,
      LocalDate dataEntregaPedido,
      LocalTime horarioRecebimentoPedido,
      LocalTime horarioEntregaPedido) {
    this.id = id;
    this.drone = drone;
    this.statusPedido = statusPedido;
    this.nomeCliente = nomeCliente;
    this.enderecoEntrega = enderecoEntrega;
    this.idVideo = idVideo;
    this.dataChegadaPedido = dataChegadaPedido;
    this.dataEntregaPedido = dataEntregaPedido;
    this.horarioRecebimentoPedido = horarioRecebimentoPedido;
    this.horarioEntregaPedido = horarioEntregaPedido;
  }

  /**
   * construtor com todos os atribudos sem o ID.
   * 
   */
  public Entrega(
      Drone drone,
      String statusPedido,
      String nomeCliente,
      String enderecoEntrega,
      String idVideo,
      LocalDate dataChegadaPedido,
      LocalDate dataEntregaPedido,
      LocalTime horarioRecebimentoPedido,
      LocalTime horarioEntregaPedido) {
    this.drone = drone;
    this.statusPedido = statusPedido;
    this.nomeCliente = nomeCliente;
    this.enderecoEntrega = enderecoEntrega;
    this.idVideo = idVideo;
    this.dataChegadaPedido = dataChegadaPedido;
    this.dataEntregaPedido = dataEntregaPedido;
    this.horarioRecebimentoPedido = horarioRecebimentoPedido;
    this.horarioEntregaPedido = horarioEntregaPedido;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Drone getDrone() {
    return this.drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

  public String getStatusPedido() {
    return this.statusPedido;
  }

  public void setStatusPedido(String statusPedido) {
    this.statusPedido = statusPedido;
  }

  public String getNomeCliente() {
    return this.nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public String getEnderecoEntrega() {
    return this.enderecoEntrega;
  }

  public void setEnderecoEntrega(String enderecoEntrega) {
    this.enderecoEntrega = enderecoEntrega;
  }

  public String getIdVideo() {
    return this.idVideo;
  }

  public void setIdVideo(String idVideo) {
    this.idVideo = idVideo;
  }

  public LocalDate getDataChegadaPedido() {
    return this.dataChegadaPedido;
  }

  public void setDataChegadaPedido(LocalDate dataChegadaPedido) {
    this.dataChegadaPedido = dataChegadaPedido;
  }

  public LocalDate getDataEntregaPedido() {
    return this.dataEntregaPedido;
  }

  public void setDataEntregaPedido(LocalDate dataEntregaPedido) {
    this.dataEntregaPedido = dataEntregaPedido;
  }

  public LocalTime getHorarioRecebimentoPedido() {
    return this.horarioRecebimentoPedido;
  }

  public void setHorarioRecebimentoPedido(LocalTime horarioRecebimentoPedido) {
    this.horarioRecebimentoPedido = horarioRecebimentoPedido;
  }

  public LocalTime getHorarioEntregaPedido() {
    return this.horarioEntregaPedido;
  }

  public void setHorarioEntregaPedido(LocalTime horarioEntregaPedido) {
    this.horarioEntregaPedido = horarioEntregaPedido;
  }

}
