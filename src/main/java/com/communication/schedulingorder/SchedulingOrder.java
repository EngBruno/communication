package com.communication.schedulingorder;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@Table(schema = "communication",name = "scheduling_order")
public class SchedulingOrder {

    @Id
    @SequenceGenerator(name = "communication.scheduling_order_id_seq", sequenceName = "communication.scheduling_order_id_seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "communication.scheduling_order_id_seq")
    private Long id;

    private LocalDate dateScheduling;
    private String message;
    private final String email;
    private final String cellPhone;
    private String typeCommunication;
    private Boolean status;


    public void activite() {
        this.status = true;
    }

    public void cancel() {
        this.status = false;
    }
}
