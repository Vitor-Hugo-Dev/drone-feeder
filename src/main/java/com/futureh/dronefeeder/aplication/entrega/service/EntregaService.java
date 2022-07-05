package com.futureh.dronefeeder.aplication.entrega.service;

import com.futureh.dronefeeder.aplication.drone.service.DroneService;
import com.futureh.dronefeeder.domain.drone.model.Drone;
import com.futureh.dronefeeder.domain.entrega.exception.EntregaInvalidaException;
import com.futureh.dronefeeder.domain.entrega.model.Entrega;
import com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.entrega.EntregaRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

  @Autowired
  EntregaRepository entregaRepository;

  @Autowired
  DroneService droneService;

  /**
   * metodo para salvar uma entrega.
   */
  public Entrega salvarEntrega(Entrega entrega)
      throws EntregaInvalidaException {

    verificaDadosDeEntrega(entrega);
    // convertendo Iterable para lista
    List<Drone> drones = StreamSupport
        .stream(droneService.retornaTodosDrones().spliterator(), false)
        .collect(Collectors.toList());

    Entrega editEntrega = entrega;
    List<Drone> dronesAtivos = drones.stream()
        .filter(drone -> drone.getStatusDrone().equals("Ativo"))
        .collect(Collectors.toList());

    dronesAtivos.get(0).addEntrega(editEntrega);
    editEntrega.setDrone(dronesAtivos.get(0));
    dronesAtivos.get(0).setStatusDrone("Ocupado");

    Entrega newEntrega = entregaRepository.save(editEntrega);

    return newEntrega;
  }

  /**
   * metodo para validar os campos.
   */
  private void verificaDadosDeEntrega(Entrega entrega)
      throws EntregaInvalidaException {

    if (entrega.getStatusPedido() == null) {
      throw new EntregaInvalidaException(
          "statusPedido não pode ser Null");
    } else if (entrega.getNomeCliente() == null) {
      throw new EntregaInvalidaException(
          "nomeCliente não pode ser Null");
    } else if (entrega.getEnderecoEntrega() == null) {
      throw new EntregaInvalidaException(
          "enderecoEntrega não pode ser Null");
    } else if (entrega.getDataChegadaPedido() == null) {
      throw new EntregaInvalidaException(
          "dataChegada não pode ser Null");
    } else if (entrega.getDataEntregaPedido() == null) {
      throw new EntregaInvalidaException(
          "dataEntregaPedido não pode ser Null");
    } else if (entrega.getHorarioRecebimentoPedido() == null) {
      throw new EntregaInvalidaException(
          "horarioRecebimentoPedido não pode ser Null");
    } else if (entrega.getHorarioEntregaPedido() == null) {
      throw new EntregaInvalidaException(
          "horarioEntregaPedido não pode ser Null");
    }
  }
}
