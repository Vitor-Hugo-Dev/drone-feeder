package com.futureh.dronefeeder.aplication.drone;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.futureh.dronefeeder.domain.drone.model.Drone;
import com.futureh.dronefeeder.infrastructure.persistence.hibernate.repository.drone.DroneRepository;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("DroneServiceTests")
public class DroneServiceTest {
  @Autowired
  private MockMvc mockMvc;
  
  @SpyBean
  private DroneRepository droneRepository;

  @Autowired
  private ObjectMapper objectMapper;
  
  @BeforeEach
  public void initEach() {
    droneRepository.deleteAll();
  }
  
  @Test
  public void salvarDroneTest() throws Exception {
    final var droneTeste = new Drone("Phantom4", new ArrayList<>());

    mockMvc.perform(MockMvcRequestBuilders.post("/drone")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(droneTeste)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
  
}
