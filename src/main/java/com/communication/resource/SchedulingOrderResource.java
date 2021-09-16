package com.communication.resource;

import com.communication.model.SchedulingOrder;
import com.communication.mapper.SchedulingOrderMapper;
import com.communication.dto.SchedulingOrderRequest;
import com.communication.dto.SchedulingOrderResponse;
import com.communication.service.SchedulingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@Api(tags = {"Api para manipular operacoes que trabalham com comunicacao"})
@SwaggerDefinition(tags = {@Tag(name = "SchedulingOrder")})
@RestController
@RequestMapping("/ordem/v1")
public class SchedulingOrderResource {

    private final SchedulingOrderService schedulingOrderService;
    private final SchedulingOrderMapper mapper;

    public SchedulingOrderResource(SchedulingOrderService schedulingOrderService, SchedulingOrderMapper mapper) {
        this.schedulingOrderService = schedulingOrderService;
        this.mapper = mapper;
    }

    @ApiOperation(value="Cria uma Ordem de comunicacao", notes="Cria uma Ordem de comunicacao para ser chamado em um determinando momento")
    @PostMapping
    public ResponseEntity<Void> createSchedulingOrder(@Valid @RequestBody SchedulingOrderRequest schedulingOrderRequest, HttpServletResponse response){
        SchedulingOrder model = this.schedulingOrderService.save(schedulingOrderRequest);
        return ResponseEntity.created(URI.create("/ordem/v1/"+model.getId())).build();
    }

    @ApiOperation(value="Recupera uma Ordem de comunicacao", notes="Recupera uma Ordem de comunicacao de acordo com o ID informado")
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingOrderResponse> createSchedulingOrder(@PathVariable(name="id",required=true) Long id){
        SchedulingOrderResponse schedulingOrderResponse = mapper.toSchedulingOrderResponse(this.schedulingOrderService.finById(id));
        return ResponseEntity.ok(schedulingOrderResponse);
    }

    @ApiOperation(value="Cancela uma Ordem de comunicacao", notes="Cancela uma Ordem de comunicacao de acordo com o ID informado")
    @PutMapping("/{id}")
    public ResponseEntity<Void> cancelSchedulingOrder(@PathVariable(name="id",required=true) Long id, HttpServletResponse response){
        SchedulingOrder model = this.schedulingOrderService.cancel(id);
        return ResponseEntity.accepted().build();
    }

}
