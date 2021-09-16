package com.communication.mapper;

import com.communication.model.SchedulingOrder;
import com.communication.dto.SchedulingOrderRequest;
import com.communication.dto.SchedulingOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel="spring")
public interface SchedulingOrderMapper {

    @Mapping(source = "dateScheduling", target = "dateScheduling", qualifiedByName = "dateSchedulingToLocalDateTime")
    SchedulingOrder toModel(SchedulingOrderRequest schedulingOrderRequest);
    SchedulingOrderResponse toSchedulingOrderResponse(SchedulingOrder model);

    @Named("dateSchedulingToLocalDateTime")
    static LocalDateTime dateSchedulingToLocalDateTime(String value) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(value,df);
    }

}
