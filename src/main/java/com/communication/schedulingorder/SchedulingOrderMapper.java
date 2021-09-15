package com.communication.schedulingorder;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface SchedulingOrderMapper {

    SchedulingOrder toModel(SchedulingOrderRequest schedulingOrderRequest);
    SchedulingOrderResponse toSchedulingOrderResponse(SchedulingOrder model);

}
