package com.futureh.dronefeeder.aplication.entrega.service;

import com.futureh.dronefeeder.aplication.drone.service.DroneService;
import com.futureh.dronefeeder.domain.drone.exception.DroneAtivoInexistenteException;
import com.futureh.dronefeeder.domain.drone.model.Drone;
import com.futureh.dronefeeder.domain.entrega.exception.EntregaInvalidaException;
import com.futureh.dronefeeder.domain.entrega.exception.EntregaNaoEncontradaException;
import com.futureh.dronefeeder.domain.entrega.exception.ListaEntregasNaoExisteException;
import com.futureh.dronefeeder.domain.entrega.model.Entrega;
import com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.drone.DroneRepository;
import com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.entrega.EntregaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {
  @Autowired
  EntregaRepository entregaRepository;

  @Autowired
  DroneRepository droneRepository;

  @Autowired
  DroneService droneService;

  /**
   * metodo para salvar uma entrega.
   *
   */
  public Entrega salvarEntrega(Entrega entrega)
      throws EntregaInvalidaException,
      DroneAtivoInexistenteException {

    verificaDadosDeEntrega(entrega);
    // convertendo Iterable para lista
    List<Drone> drones = StreamSupport
        .stream(droneService.retornaTodosDrones().spliterator(), false)
        .collect(Collectors.toList());

    Entrega editEntrega = entrega;

    List<Drone> dronesAtivos = drones.stream()
        .filter(drone -> drone.getStatusDrone().equals("Ativo"))
        .collect(Collectors.toList());

    if (dronesAtivos.isEmpty()) {
      throw new DroneAtivoInexistenteException();
    }

    Drone primeiroDroneLivre = dronesAtivos.get(0);

    primeiroDroneLivre.addEntrega(editEntrega);
    editEntrega.setDrone(primeiroDroneLivre);
    primeiroDroneLivre.setStatusDrone("Ocupado");
    droneRepository.save(primeiroDroneLivre);

    Entrega newEntrega = entregaRepository.save(editEntrega);

    return newEntrega;
  }

  /**
   * metodo para buscar uma entrega por id.
   */
  public Entrega buscarEntregaPorId(Integer id)
      throws EntregaNaoEncontradaException {
    Optional<Entrega> entrega = entregaRepository.findById(id);
    if (entrega.isEmpty()) {
      throw new EntregaNaoEncontradaException();
    }
    return entrega.get();
  }

  /**
   * retorna uma lista de entregas.
   */
  public List<Entrega> retornarTodasEntregas()
      throws ListaEntregasNaoExisteException {
    List<Entrega> entregas = StreamSupport
        .stream(entregaRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());

    if (entregas.size() <= 0) {
      throw new ListaEntregasNaoExisteException();
    }

    return entregas;
  }

  /**
   * Javadoc.
   */
  public Entrega atualizaEntrega(Integer id, String dataEntrega, String horarioEntrega)
      throws EntregaNaoEncontradaException {
    Entrega entrega = buscarEntregaPorId(id);

    entrega.setDataEntregaPedido(LocalDate.parse(dataEntrega));
    entrega.setHorarioEntregaPedido(LocalTime.parse(horarioEntrega));
    entrega.setStatusPedido("Entregue");

    Drone drone = entrega.getDrone();
    drone.setStatusDrone("Ativo");

    droneRepository.save(drone);

    return entregaRepository.save(entrega);
  }

  /**
   * deleta uma entrega por id.
   */
  public void deletarEntrega(Integer id)
      throws EntregaNaoEncontradaException {
    Optional<Entrega> entrega = entregaRepository.findById(id);
    if (entrega.isEmpty()) {
      throw new EntregaNaoEncontradaException();
    }
    entregaRepository.delete(entrega.get());
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
