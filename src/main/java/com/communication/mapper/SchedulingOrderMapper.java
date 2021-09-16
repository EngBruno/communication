package com.communication.mapper;

import com.communication.model.SchedulingOrder;
import com.communication.dto.SchedulingOrderRequest;
import com.communication.dto.SchedulingOrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface SchedulingOrderMapper {

    SchedulingOrder toModel(SchedulingOrderRequest schedulingOrderRequest);
    SchedulingOrderResponse toSchedulingOrderResponse(SchedulingOrder model);

}
