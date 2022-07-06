package com.futureh.dronefeeder.aplication.entrega.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.futureh.dronefeeder.domain.drone.model.Drone;
import com.futureh.dronefeeder.domain.entrega.exception.EntregaInvalidaException;
import com.futureh.dronefeeder.domain.entrega.model.Entrega;

@SpringBootTest
@AutoConfigureMockMvc
public class EntregaControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private static Drone drone = new Drone("Phanton3",
      new ArrayList<>());
  private static String statusPedido = "chegou a transportadora";
  private static String nomeCliente = "rogerinho DragonBorn";
  private static String enderecoEntrega = "-8.4425525,-35.0157131";
  private static String idVideo = "";
  private static LocalDate dataChegadaPedido = LocalDate.parse("2022-10-20");
  private static LocalDate dataEntregaPedido = LocalDate.parse("2022-10-22");
  private static LocalTime horarioRecebimentoPedido = LocalTime.parse("10:00:00");
  private static LocalTime horarioEntregaPedido = LocalTime.parse("12:00:00");

  private Entrega mockEntregaRetorno = new Entrega();

  private Entrega mockEntregaBody = new Entrega();

  @BeforeEach
  public void initEach() throws EntregaInvalidaException {
    mockEntregaRetorno.setDrone(drone);
    mockEntregaRetorno.setStatusPedido(statusPedido);
    mockEntregaRetorno.setNomeCliente(nomeCliente);
    mockEntregaRetorno.setEnderecoEntrega(enderecoEntrega);
    mockEntregaRetorno.setIdVideo(idVideo);
    mockEntregaRetorno.setDataChegadaPedido(dataChegadaPedido);
    mockEntregaRetorno.setDataEntregaPedido(dataEntregaPedido);
    mockEntregaRetorno.setHorarioRecebimentoPedido(horarioRecebimentoPedido);
    mockEntregaRetorno.setHorarioEntregaPedido(horarioEntregaPedido);

    mockEntregaBody.setStatusPedido(statusPedido);
    mockEntregaBody.setNomeCliente(nomeCliente);
    mockEntregaBody.setEnderecoEntrega(enderecoEntrega);
    mockEntregaBody.setIdVideo(idVideo);
    mockEntregaBody.setDataChegadaPedido(dataChegadaPedido);
    mockEntregaBody.setDataEntregaPedido(dataEntregaPedido);
    mockEntregaBody.setHorarioRecebimentoPedido(horarioRecebimentoPedido);
    mockEntregaBody.setHorarioEntregaPedido(horarioEntregaPedido);

    // montando mock do drone

    // Iterable<Drone> mockDroneIterable = Arrays.asList(drone);

    // Mockito.when(entregaService.salvarEntrega(mockEntregaBody)).thenReturn(mockEntregaRetorno);

  }

  @Test
  @DisplayName("Testa se ao salvar uma entidade valida retorna status 200 e a entidade salva")
  public void testSalvarEntrega() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/entrega")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(mockEntregaBody)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
