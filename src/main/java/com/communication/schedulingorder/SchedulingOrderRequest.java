package com.communication.schedulingorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SchedulingOrderRequest {

    private final LocalDate dateScheduling;
    private final String message;
    private final String email;
    private final String cellPhone;
    private final String typeCommunication;

}
