package com.communication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class SchedulingOrderResponse {

    private final Long id;
    @JsonProperty("date_scheduling")
    private final LocalDateTime dateScheduling;
    private final String receiver;
    private final String message;
    private final String email;
    private final String cellPhone;
    @JsonProperty("type_communication")
    private final String typeCommunication;
    private final Boolean activity;

}
