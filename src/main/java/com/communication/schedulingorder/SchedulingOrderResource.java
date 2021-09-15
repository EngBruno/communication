package com.communication.schedulingorder;

import ch.qos.logback.core.joran.util.beans.BeanDescriptionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("ordem/v1")
public class SchedulingOrderResource {

    private final SchedulingOrderService schedulingOrderService;
    private final SchedulingOrderMapper mapper;

    public SchedulingOrderResource(SchedulingOrderService schedulingOrderService, SchedulingOrderMapper mapper) {
        this.schedulingOrderService = schedulingOrderService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> createSchedulingOrder(@RequestBody SchedulingOrderRequest schedulingOrderRequest, HttpServletResponse response){
        SchedulingOrder model = this.schedulingOrderService.save(mapper.toModel(schedulingOrderRequest));
        return ResponseEntity.created(URI.create("/conta_bancaria/"+model.getId())).build();
    }


}
