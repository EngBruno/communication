package com.communication.schedulingorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
public class SchedulingOrderResponse {

    private final Long id;
    @JsonProperty("date_scheduling")
    private final LocalDate dateScheduling;
    private final String receiver;
    private final String message;
    private final String email;
    private final String cellPhone;
    @JsonProperty("type_communication")
    private final String typeCommunication;

}
