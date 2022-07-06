package com.futureh.dronefeeder.domain.drone.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.futureh.dronefeeder.domain.entrega.model.Entrega;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_drone")
// https://pt.stackoverflow.com/questions/242288
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, 
    property = "id")
public class Drone {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String statusDrone;

  private String localizacaoDrone;

  private String modelo;

  @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Entrega> entregas = new ArrayList<Entrega>();

  /**
   * construtor vazio.
   */
  public Drone() {

  }

  /**
   * construtor com todos os atributos necessários.
   */
  public Drone(Integer id,
      String modelo,
      List<Entrega> entregas) {

    this.id = id;
    this.statusDrone = "Ativo";
    this.localizacaoDrone = "Base";
    this.modelo = modelo;
    this.entregas = entregas;
  }

  /**
   * construtor com todos os atributos necessários, menos o ID.
   */
  public Drone(
      String modelo,
      List<Entrega> entregas) {
    
    this.statusDrone = "Ativo";
    this.localizacaoDrone = "Base";
    this.modelo = modelo;
    this.entregas = entregas;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatusDrone() {
    return this.statusDrone;
  }

  public void setStatusDrone(String statusDrone) {
    this.statusDrone = statusDrone;
  }

  public String getLocalizacaoDrone() {
    return this.localizacaoDrone;
  }

  public void setLocalizacaoDrone(String localizacaoDrone) {
    this.localizacaoDrone = localizacaoDrone;
  }

  public String getModelo() {
    return this.modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public void addEntrega(Entrega entrega) {
    entregas.add(entrega);
  }

  public List<Entrega> getEntregas() {
    return this.entregas;
  }

  public void setEntregas(List<Entrega> entregas) {
    this.entregas = entregas;
  }
  
  
}
