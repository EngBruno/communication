package com.communication.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "communication",name = "scheduling_order")
public class SchedulingOrder {

    @Id
    @SequenceGenerator(name = "communication.scheduling_order_id_seq", sequenceName = "communication.scheduling_order_id_seq", allocationSize = 1, initialValue = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "communication.scheduling_order_id_seq")
    private Long id;

    private LocalDateTime dateScheduling;
    private String message;
    private String email;
    private String cellPhone;
    private String typeCommunication;
    private Boolean activity;


    public void activite() {
        this.activity = true;
    }

    public void cancel() {this.activity = false;}
}
