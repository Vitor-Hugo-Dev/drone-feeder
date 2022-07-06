package com.futureh.dronefeeder.aplication.entrega.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.futureh.dronefeeder.aplication.drone.service.DroneService;
import com.futureh.dronefeeder.domain.drone.exception.DroneAtivoInexistenteException;
import com.futureh.dronefeeder.domain.drone.model.Drone;
import com.futureh.dronefeeder.domain.entrega.exception.EntregaInvalidaException;
import com.futureh.dronefeeder.domain.entrega.model.Entrega;
import com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.entrega.EntregaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("EntregaServiceTests")
public class EntregaServiceTests {

  @MockBean
  EntregaRepository entregaRepository;

  @MockBean
  DroneService droneService;

  @Autowired
  private EntregaService entregaService;

  private static Drone drone = new Drone();
  private static String statusPedido = "chegou a transportadora";
  private static String nomeCliente = "rogerinho DragonBorn";
  private static String enderecoEntrega = "-8.4425525,-35.0157131";
  private static String idVideo = "";
  private static LocalDate dataChegadaPedido = LocalDate.parse("2022-10-20");
  private static LocalDate dataEntregaPedido = LocalDate.parse("2022-10-22");
  private static LocalTime horarioRecebimentoPedido = LocalTime.parse("10:00:00");
  private static LocalTime horarioEntregaPedido = LocalTime.parse("12:00:00");

  private Drone mockDrone = new Drone("Phanton3", new ArrayList<>());

  private Entrega mockEntrega = new Entrega();

  @BeforeEach
  public void initEach() {
    // setando valores da entrega
    mockEntrega.setDrone(drone);
    mockEntrega.setStatusPedido(statusPedido);
    mockEntrega.setNomeCliente(nomeCliente);
    mockEntrega.setEnderecoEntrega(enderecoEntrega);
    mockEntrega.setIdVideo(idVideo);
    mockEntrega.setDataChegadaPedido(dataChegadaPedido);
    mockEntrega.setDataEntregaPedido(dataEntregaPedido);
    mockEntrega.setHorarioRecebimentoPedido(horarioRecebimentoPedido);
    mockEntrega.setHorarioEntregaPedido(horarioEntregaPedido);

    // montando mock do drone
    Iterable<Drone> mockDroneIterable = Arrays.asList(mockDrone);

    Mockito.when(droneService.retornaTodosDrones()).thenReturn(mockDroneIterable);
    Mockito.when(entregaRepository.save(mockEntrega)).thenReturn(mockEntrega);
  }

  @AfterEach
  public void finishEach() {
    mockEntrega = new Entrega();
  }

  @Test
  @DisplayName("Testa se uma entrega é criada com sucesso!")
  public void testSalvarEntrega()
      throws EntregaInvalidaException,
      DroneAtivoInexistenteException {
    Entrega newEntrega = entregaService.salvarEntrega(mockEntrega);
    Assertions.assertNotNull(newEntrega);
  }

  @Test
  @DisplayName("Testa se é lançado um erro específico ao tentar criar uma entrega com dados incompletos")
  public void testErroSalvarEntrega() throws EntregaInvalidaException {
    mockEntrega.setNomeCliente(null);

    Assertions.assertThrows(EntregaInvalidaException.class, () -> entregaService.salvarEntrega(mockEntrega));
  }
}
