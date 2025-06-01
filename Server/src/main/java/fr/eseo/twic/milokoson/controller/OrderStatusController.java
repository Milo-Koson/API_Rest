package fr.eseo.twic.milokoson.controller;

import fr.eseo.twic.milokoson.bo.OrderStatus;
import fr.eseo.twic.milokoson.dao.OrderStatusRepository;
import fr.eseo.twic.milokoson.dto.OrderStatus.OrderStatusDto;
import fr.eseo.twic.milokoson.dto.OrderStatus.OrderStatusMapper;
import fr.eseo.twic.milokoson.exception.OrderStatusAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v{apiversion}")
public class OrderStatusController {

    @Autowired
    private OrderStatusRepository repository;

    @Autowired
    private OrderStatusMapper mapper;

    @GetMapping("/orders/orderStatuses")
    public ResponseEntity<Iterable<OrderStatusDto>> readOrderStatuses() {
        return ResponseEntity.ok(mapper.bo2dtoList(repository.findAll()));
    }

    @PostMapping("/orders/orderStatuses")
    ResponseEntity<OrderStatusDto> createOrderStatuses(@RequestBody OrderStatusDto requestBody){
        Optional<OrderStatus> existing = repository.findById(requestBody.getOrderStatusId());
        if(existing.isEmpty()){
            OrderStatus created = repository.save(OrderStatus.from(requestBody));
            return new ResponseEntity<>(mapper.bo2dto(created), HttpStatus.CREATED);
        } else {
            throw new OrderStatusAlreadyExistsException(requestBody.getOrderStatusId());
        }
    }
}
