package com.communication.schedulingorder;

import org.springframework.stereotype.Service;

@Service
public class SchedulingOrderService {

    private  final SchedulingOrderRepository schedulingOrderRepository;
    private final SchedulingOrderMapper mapper;

    public SchedulingOrderService(SchedulingOrderRepository schedulingOrderRepository, SchedulingOrderMapper mapper) {
        this.schedulingOrderRepository = schedulingOrderRepository;
        this.mapper = mapper;
    }

    public SchedulingOrder save(SchedulingOrderRequest schedulingOrderRequest){
        return schedulingOrderRepository.save(mapper.toModel(schedulingOrderRequest));
    }

}
