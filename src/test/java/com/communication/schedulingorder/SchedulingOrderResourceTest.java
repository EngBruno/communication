package com.communication.schedulingorder;

import com.communication.config.PostgresqlContainerCustom;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SchedulingOrderResourceTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainerCustom.getInstance();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateSchedulingOrder() throws Exception {

        SchedulingOrderRequest schedulingOrderResponse = SchedulingOrderRequest.builder()
                .cellPhone("85997844388")
                .email("test@gmail.com")
                .dateScheduling(LocalDate.now())
                .message("Mensagem de test")
                .typeCommunication("SMS")
                .build();

        mockMvc.perform(post("/ordem/v1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(schedulingOrderResponse)))
                .andExpect(status().isCreated());

    }

}
