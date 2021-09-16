package com.communication.resource;

import com.communication.model.SchedulingOrder;
import com.communication.mapper.SchedulingOrderMapper;
import com.communication.dto.SchedulingOrderRequest;
import com.communication.dto.SchedulingOrderResponse;
import com.communication.dto.SchedulingOrderStatusRequest;
import com.communication.service.SchedulingOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/ordem/v1")
public class SchedulingOrderResource {

    private final SchedulingOrderService schedulingOrderService;
    private final SchedulingOrderMapper mapper;

    public SchedulingOrderResource(SchedulingOrderService schedulingOrderService, SchedulingOrderMapper mapper) {
        this.schedulingOrderService = schedulingOrderService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> createSchedulingOrder(@Valid @RequestBody SchedulingOrderRequest schedulingOrderRequest, HttpServletResponse response){
        SchedulingOrder model = this.schedulingOrderService.save(schedulingOrderRequest);
        return ResponseEntity.created(URI.create("/ordem/"+model.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingOrderResponse> createSchedulingOrder(@PathVariable(name="id",required=true) Long id){
        SchedulingOrderResponse schedulingOrderResponse = mapper.toSchedulingOrderResponse(this.schedulingOrderService.finById(id).get());
        return ResponseEntity.ok(schedulingOrderResponse);
    }

    @PutMapping
    public ResponseEntity<Void> cancelSchedulingOrder(@Valid @RequestBody SchedulingOrderStatusRequest schedulingOrderStatusRequest, HttpServletResponse response){
        SchedulingOrder model = this.schedulingOrderService.cancel(schedulingOrderStatusRequest);
        return ResponseEntity.created(URI.create("/conta_bancaria/"+model.getId())).build();
    }

}
