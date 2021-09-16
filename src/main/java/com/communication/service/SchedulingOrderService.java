package com.communication.service;

import com.communication.exceptionHandler.CreateMessageExeception;
import com.communication.model.SchedulingOrder;
import com.communication.repository.SchedulingOrderRepository;
import com.communication.mapper.SchedulingOrderMapper;
import com.communication.dto.SchedulingOrderRequest;
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
        SchedulingOrder schedulingOrder = mapper.toModel(schedulingOrderRequest);
        schedulingOrder.activite();
        return schedulingOrderRepository.save(schedulingOrder);
    }

    public SchedulingOrder finById(Long id){
        return  this.schedulingOrderRepository.findById(id).orElseThrow(() -> CreateMessageExeception.createObjectMessageNotFoundExeception("messagem.notfound",""+id));
    }

    public SchedulingOrder cancel(Long id) {
        SchedulingOrder schedulingOrder = this.finById(id);
        schedulingOrder.cancel();
        return schedulingOrderRepository.save(schedulingOrder);
    }
}
