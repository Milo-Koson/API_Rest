package fr.eseo.twic.milokoson.controller;

import fr.eseo.twic.milokoson.bo.Order;
import fr.eseo.twic.milokoson.bo.OrderDetails;
import fr.eseo.twic.milokoson.dao.OrderDetailsRepository;
import fr.eseo.twic.milokoson.dao.OrderRepository;
import fr.eseo.twic.milokoson.dto.OrderDetails.OrderDetailsDto;
import fr.eseo.twic.milokoson.dto.OrderDetails.OrderDetailsMapper;
import fr.eseo.twic.milokoson.exception.MismatchedBodyTargetException;
import fr.eseo.twic.milokoson.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v{apiversion}")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsMapper mapper;

    @GetMapping("/orders/{orderId}/orderDetails")
    public ResponseEntity<List<OrderDetailsDto>> listOrderDetailsByOrderId(@PathVariable String orderId){
        List<OrderDetailsDto> existing = orderDetailsRepository.findOrderDetailsByOrderId(orderId).stream().map(mapper::bo2dto).collect(Collectors.toList());
        return ResponseEntity.ok(existing);
    }

    @PostMapping("/orders/{orderId}/orderDetails")
    ResponseEntity<OrderDetailsDto> createOrderDetails(@PathVariable String orderId, @RequestBody List<OrderDetailsDto> requestBodies) {
        if (requestBodies == null || requestBodies.isEmpty()) {
            throw new IllegalArgumentException("Invalid request body");
        }
        for (OrderDetailsDto requestBody : requestBodies) {
            Optional<Order> existingOrder = orderRepository.findById(orderId);
            if (existingOrder.isEmpty()) {
                throw new OrderNotFoundException(orderId);
            }

            OrderDetails newOrderDetails = new OrderDetails();
            newOrderDetails.setOrderId(existingOrder.get());
            newOrderDetails.setProductId(requestBody.getProductId());
            newOrderDetails.setQuantity(requestBody.getQuantity());

            orderDetailsRepository.save(newOrderDetails);
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/orders/{orderId}/orderDetails")
    ResponseEntity<OrderDetailsDto> updateOrderDetailsByOrderId(@PathVariable String orderId, @RequestBody OrderDetailsDto requestBody) {
        if (!orderId.equalsIgnoreCase(requestBody.getOrderDetailId())) {
            throw new MismatchedBodyTargetException(requestBody.getOrderDetailId(), orderId);
        }
        Optional<OrderDetails> existing = orderDetailsRepository.findById(orderId);
        if (existing.isEmpty()) {
            OrderDetails created = orderDetailsRepository.save(OrderDetails.from(requestBody));
            return new ResponseEntity<>(mapper.bo2dto(created), HttpStatus.CREATED);
        } else {
            orderDetailsRepository.save(existing.get().updateFrom(OrderDetails.from(requestBody)));
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/orders/{orderId}/orderDetails")
    ResponseEntity<Void> deleteOrderDetailsByOrderId(@PathVariable String orderId) {
        Optional<OrderDetails> existing = orderDetailsRepository.findById(orderId);
        orderDetailsRepository.delete(existing.orElseThrow(() -> new OrderNotFoundException(orderId)));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orderDetails")
    public ResponseEntity<Iterable<OrderDetailsDto>> readOrderDetails() {
        return ResponseEntity.ok(mapper.bo2dtoList(orderDetailsRepository.findAll()));
    }

}
