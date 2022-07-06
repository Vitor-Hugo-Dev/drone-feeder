package com.futureh.dronefeeder.aplication.drone.service;

import com.futureh.dronefeeder.domain.drone.exception.DroneInexistenteException;
import com.futureh.dronefeeder.domain.drone.exception.DroneInvalidoException;
import com.futureh.dronefeeder.domain.drone.model.Drone;
import com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.drone.DroneRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

  @Autowired
  DroneRepository droneRepository;

  /**
   * Cria um novo Drone.
   */
  public Drone salvaDrone(Drone novoDrone) throws DroneInvalidoException {
    if (novoDrone.getModelo().isEmpty()
        || !novoDrone.getLocalizacaoDrone().equals("Base")
        || !novoDrone.getStatusDrone().equals("Ativo")) {

      throw new DroneInvalidoException();
    }

    Drone droneSalvo = droneRepository.save(novoDrone);

    return droneSalvo;
  }

  /**
   * Retorna Drone existente pelo ID.
   */
  public Drone retornaDronePeloId(Integer id) {
    Optional<Drone> droneById = droneRepository.findById(id);

    if (!droneById.isEmpty()) {
      return droneById.get();

    } else {
      throw new DroneInexistenteException();
    }
  }

  /**
   * Retorna todos os Drones.
   */
  public Iterable<Drone> retornaTodosDrones() {
    return droneRepository.findAll();
  }

  /**
   * Atualiza status Drone existente.
   */
  public Drone atualizaStatusDrone(Integer id, String novoStatus) {
    Drone droneParaAtualizar = retornaDronePeloId(id);

    droneParaAtualizar.setStatusDrone(novoStatus);

    return droneParaAtualizar;
  }

  /**
   * Atualiza localização Drone existente.
   */
  public Drone atualizaLocalizacaoDrone(Integer id, String novaLocalizacao) {
    Drone droneParaAtualizar = retornaDronePeloId(id);

    droneParaAtualizar.setLocalizacaoDrone(novaLocalizacao);

    return droneParaAtualizar;
  }

  /**
   * Deleta Drone existente.
   */
  public String deletaDrone(Integer id) {
    Optional<Drone> droneById = droneRepository.findById(id);

    if (!droneById.isEmpty()) {
      droneRepository.deleteById(id);

      String deletadoComSucesso = "Drone deletado com sucesso.";
      return deletadoComSucesso;

    } else {
      throw new DroneInexistenteException();
    }
  }
}
