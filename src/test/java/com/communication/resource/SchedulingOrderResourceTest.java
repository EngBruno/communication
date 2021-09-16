package com.communication.resource;

import com.communication.config.PostgresqlContainerCustom;
import com.communication.dto.SchedulingOrderRequest;
import com.communication.enums.TypeCommunication;
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
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                .dateScheduling("2021-10-10 12:30")
                .message("Mensagem de test")
                .typeCommunication(TypeCommunication.sms)
                .build();

        MvcResult mockMvc1 =   mockMvc.perform(post("/ordem/v1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(schedulingOrderResponse)))
                .andExpect(status().isCreated())
                .andReturn();

        String path = mockMvc1.getResponse().getHeader("Location").toString();
        mockMvc.perform(get(path)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@gmail.com"));

    }

    @Test
    public void testFindByIdNotFound () throws Exception {
        mockMvc.perform(get("/ordem/v1/"+"201")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCancel () throws Exception {

        SchedulingOrderRequest schedulingOrderResponse = SchedulingOrderRequest.builder()
                .cellPhone("85997844388")
                .email("test@gmail.com")
                .dateScheduling("2021-10-10 12:30")
                .message("Mensagem de test")
                .typeCommunication(TypeCommunication.sms)
                .build();

        MvcResult mockMvc1 =   mockMvc.perform(post("/ordem/v1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(schedulingOrderResponse)))
                .andExpect(status().isCreated())
                .andReturn();

        String path = mockMvc1.getResponse().getHeader("Location").toString();

        mockMvc.perform(put(path)
                        .contentType("application/json"))
                .andExpect(status().isAccepted());

        mockMvc.perform(get(path)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.activity").value(false));
    }

    @Test
    public void testValidateFieldEmail () throws Exception {
        SchedulingOrderRequest schedulingOrderResponse = SchedulingOrderRequest.builder()
                .cellPhone("85997844388")
                .email("test")
                .dateScheduling("2021-10-10 12:30")
                .message("Mensagem de test")
                .typeCommunication(TypeCommunication.sms)
                .build();

        mockMvc.perform(post("/ordem/v1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(schedulingOrderResponse)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].status").value("400"))
                .andExpect(jsonPath("$.[0].messageErro").value("Email com formatacao invalida"))
                .andExpect(jsonPath("$.[0].value").value("email"));
    }

    @Test
    public void testValidateFieldCellPhone () throws Exception {
        SchedulingOrderRequest schedulingOrderResponse = SchedulingOrderRequest.builder()
                .cellPhone("(85)997844388")
                .email("test@gmail.com")
                .dateScheduling("2021-10-10 12:30")
                .message("Mensagem de test")
                .typeCommunication(TypeCommunication.sms)
                .build();

        mockMvc.perform(post("/ordem/v1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(schedulingOrderResponse)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].status").value("400"))
                .andExpect(jsonPath("$.[0].messageErro").value("Erro na formatacao do cell phone, somente numeros com ate 11 digitos"))
                .andExpect(jsonPath("$.[0].value").value("cellPhone"));
    }


    @Test
    public void testValidateFieldDateScheduling () throws Exception {

        SchedulingOrderRequest schedulingOrderResponse = SchedulingOrderRequest.builder()
                .cellPhone("(85)997844388")
                .email("test@gmail.com")
                .dateScheduling("2021-10-1012:30")
                .message("Mensagem de test")
                .typeCommunication(TypeCommunication.sms)
                .build();

        mockMvc.perform(post("/ordem/v1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(schedulingOrderResponse)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.messageErro").value("Mensagem invalida"))
                .andExpect(jsonPath("$.value").value("dateScheduling"));
    }



}
