package com.communication.service;

import com.communication.model.SchedulingOrder;
import com.communication.repository.SchedulingOrderRepository;
import com.communication.mapper.SchedulingOrderMapper;
import com.communication.dto.SchedulingOrderRequest;
import com.communication.dto.SchedulingOrderStatusRequest;
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
