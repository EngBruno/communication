package com.communication.dto;

import com.communication.deserializer.CustomJsonDeserializerDate;
import com.communication.enums.TypeCommunication;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "2021-10-10 12:30",required = true)
    @NotNull
    @JsonDeserialize(using= CustomJsonDeserializerDate.class)
    private  String dateScheduling;

    @ApiModelProperty(example = "Test",required = true)
    private String message;

    @ApiModelProperty(example = "test@gmail.com",required = true)
    @Email
    private  String email;

    @ApiModelProperty(example = "85997844388",required = true)
    @Pattern(regexp="(^$|[0-9]{11})",message = "Erro na formatacao do cell phone, somente numeros com ate 11 digitos")
    private  String cellPhone;

    @ApiModelProperty(example = "sms",required = true)
    private  TypeCommunication typeCommunication;

}
