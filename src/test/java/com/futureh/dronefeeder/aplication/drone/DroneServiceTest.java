package com.futureh.dronefeeder.aplication.drone;

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
  
  @BeforeEach
  public void initEach() {
    droneRepository.deleteAll();
  }
  
  @Test
  public void salvarDroneTest() throws Exception {
    final var droneTeste = new Drone("Phantom4", null);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/drone")
        .content(asJsonString(droneTeste)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
  }
  
}
