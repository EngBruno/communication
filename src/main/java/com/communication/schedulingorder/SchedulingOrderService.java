package com.communication.schedulingorder;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchedulingOrderService {

    private  final SchedulingOrderRepository schedulingOrderRepository;
    private final SchedulingOrderMapper mapper;

    public SchedulingOrderService(SchedulingOrderRepository schedulingOrderRepository, SchedulingOrderMapper mapper) {
        this.schedulingOrderRepository = schedulingOrderRepository;
        this.mapper = mapper;
    }

    public SchedulingOrder save(SchedulingOrderRequest schedulingOrderRequest){
        SchedulingOrder schedulingOrder = mapper.toModel(schedulingOrderRequest);
        schedulingOrder.activite();
        return schedulingOrderRepository.save(schedulingOrder);
    }

    public Optional<SchedulingOrder> finById(Long id){
        return  this.schedulingOrderRepository.findById(id);
    }

    public SchedulingOrder cancel(SchedulingOrderStatusRequest schedulingOrderStatusRequest) {
        SchedulingOrder schedulingOrder = schedulingOrderRepository.findById(schedulingOrderStatusRequest.getId()).get();
        schedulingOrder.cancel();
        return schedulingOrderRepository.save(schedulingOrder);
    }
}
