package com.communication.schedulingorder;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SchedulingOrderRequest {

    private final LocalDate dateScheduling;
    private final String receiver;
    private final String mensage;
    private final String typeCommunication;

}
