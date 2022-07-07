package com.futureh.dronefeeder.aplication.drone;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.futureh.dronefeeder.domain.drone.model.Drone;
import com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.drone.DroneRepository;
import com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.entrega.EntregaRepository;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Drone Tests")
public class DroneTest {
  @Autowired
  private MockMvc mockMvc;
  
  @SpyBean
  private EntregaRepository entregaRepository;
  
  @SpyBean
  private DroneRepository droneRepository;

  @Autowired
  private ObjectMapper objectMapper;
  
  @BeforeEach
  public void initEach() {
    droneRepository.deleteAll();
    entregaRepository.deleteAll();
  }
  
  @Test
  public void salvarDroneTest() throws Exception {
    final var droneTeste = new Drone("Phantom4", new ArrayList<>());

    mockMvc.perform(MockMvcRequestBuilders.post("/drone")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(droneTeste)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }
  
  @Test
  public void salvarDroneComDadosErradosTest() throws Exception {
    final var droneTeste = new Drone("", new ArrayList<>());
    
    mockMvc.perform(MockMvcRequestBuilders.post("/drone")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(droneTeste)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
  
  @Test
  public void retornarDronePeloIdComSucesso() throws Exception {
    final var droneTeste = new Drone("Phantom4", new ArrayList<>());
    
    droneRepository.save(droneTeste);
    
    List<Drone> drones =  (List<Drone>) droneRepository.findAll();
    Integer idFromFirstDrone = drones.get(0).getId();

    mockMvc.perform(MockMvcRequestBuilders.get("/drone/{droneId}", idFromFirstDrone)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(droneTeste)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  @Test
  public void retornarDronePeloIdInexistente() throws Exception {
     final var droneTeste = new Drone("Phantom4", new ArrayList<>());
         
     droneRepository.save(droneTeste);
     
     List<Drone> drones =  (List<Drone>) droneRepository.findAll();
     Integer idFromFirstDrone = drones.get(0).getId() + 1;
 
     mockMvc.perform(MockMvcRequestBuilders.get("/drone/{droneId}", idFromFirstDrone)
         .contentType("application/json")
         .content(objectMapper.writeValueAsString(droneTeste)))
         .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
  
  @Test
  public void retornaTodosOsDrones() throws Exception {
    final var droneTeste = new Drone("Phantom4", new ArrayList<>());
    final var droneTeste2 = new Drone("Phantom5", new ArrayList<>());
    
    droneRepository.save(droneTeste);
    droneRepository.save(droneTeste2);
    
    mockMvc.perform(MockMvcRequestBuilders.get("/drone")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(droneTeste))
        .content(objectMapper.writeValueAsString(droneTeste2)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].modelo").value(droneTeste.getModelo()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].modelo").value(droneTeste2.getModelo()));
  }
  
  @Test
  public void retornaListaVaziaCasoNaoTenhaDrones() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/drone")
        .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string(containsString("[]")));
  }
  
  @Test
  public void atualizaStatusDrone() throws Exception {
    final var droneTeste = new Drone("Phantom4", new ArrayList<>());
    droneRepository.save(droneTeste);
    
    List<Drone> drones =  (List<Drone>) droneRepository.findAll();
    Integer idFromFirstDrone = drones.get(0).getId();
    String statusDrone = "ocupado";
    
    mockMvc.perform(MockMvcRequestBuilders.post("/drone/{droneId}/status/{statusDrone}", idFromFirstDrone, statusDrone)
        .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.statusDrone").value("ocupado"));
  }
  
  
  @Test
  public void atualizarStatusInvalidoDrone() throws Exception {
    final var droneTeste = new Drone("Phantom4", new ArrayList<>());
    droneRepository.save(droneTeste);
    
    List<Drone> drones =  (List<Drone>) droneRepository.findAll();
    Integer idFromFirstDrone = drones.get(0).getId();
    String statusDrone = "StatusInvalido";
    
    mockMvc.perform(MockMvcRequestBuilders.post("/drone/{droneId}/status/{statusDrone}", idFromFirstDrone, statusDrone)
        .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
  
  @Test
  public void atualizarLocalizacaoDrone() throws Exception {
    final var droneTeste = new Drone("Phantom4", new ArrayList<>());
    droneRepository.save(droneTeste);
    
    List<Drone> drones =  (List<Drone>) droneRepository.findAll();
    Integer idFromFirstDrone = drones.get(0).getId();
    String localizacaoDrone = "-28.4425525,-45.0157131";
    
    mockMvc.perform(MockMvcRequestBuilders.post("/drone/{droneId}/localizacao/{localizacaoDrone}", idFromFirstDrone, localizacaoDrone)
        .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.localizacaoDrone").value(localizacaoDrone));
  }
  
  @Test
  public void deletaDronePeloId() throws Exception {
    final var droneTeste = new Drone("Phantom4", new ArrayList<>());
    droneRepository.save(droneTeste);
    
    List<Drone> drones =  (List<Drone>) droneRepository.findAll();
    Integer idFromFirstDrone = drones.get(0).getId();
    
    mockMvc.perform(MockMvcRequestBuilders.delete("/drone/{droneId}", idFromFirstDrone)
        .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("Drone deletado com sucesso."));
    
  }
  
  
  
}
