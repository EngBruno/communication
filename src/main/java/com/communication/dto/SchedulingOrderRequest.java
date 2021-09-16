package com.communication.dto;

import com.communication.CustomJsonDeserializerDate;
import com.communication.enums.TypeCommunication;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingOrderRequest {

    @NotNull
    @JsonDeserialize(using= CustomJsonDeserializerDate.class)
    private  String dateScheduling;
    private String message;
    @Email
    private  String email;
    @Pattern(regexp="(^$|[0-9]{11})",message = "Erro na formatacao do cell phone, somente numeros com ate 11 digitos")
    private  String cellPhone;
    private  TypeCommunication typeCommunication;

}
