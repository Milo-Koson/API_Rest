package fr.eseo.twic.milokoson.controller;

import fr.eseo.twic.milokoson.bo.Customer;
import fr.eseo.twic.milokoson.bo.Order;
import fr.eseo.twic.milokoson.bo.OrderStatus;
import fr.eseo.twic.milokoson.dao.CustomerRepository;
import fr.eseo.twic.milokoson.dao.OrderRepository;
import fr.eseo.twic.milokoson.dao.OrderStatusRepository;
import fr.eseo.twic.milokoson.dto.Order.OrderDto;
import fr.eseo.twic.milokoson.dto.Order.OrderMapper;
import fr.eseo.twic.milokoson.exception.OrderAlreadyExistsException;
import fr.eseo.twic.milokoson.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v{apiversion}")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderMapper mapper;

    LocalDate localDateToday = LocalDate.now();
    Date today = Date.from(localDateToday.atStartOfDay(ZoneId.systemDefault()).toInstant());
    String dateString = "9999-12-31";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    LocalDate localDateFuture = LocalDate.parse(dateString);
    LocalDate localDateYesterday = LocalDate.now().minusDays(1);
    Date yesterday = Date.from(localDateYesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());

    public OrderController() throws ParseException {
    }

    @GetMapping("/orders")
    public ResponseEntity<Iterable<OrderDto>> readOrders() {
        return ResponseEntity.ok(mapper.bo2dtoList(orderRepository.findAll()));
    }

    @PostMapping("/orders")
    ResponseEntity<OrderDto> createOrders(@RequestBody OrderDto requestBody) {
        if (requestBody == null || requestBody.getOrderId() == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        Optional<Order> existing = orderRepository.findById(requestBody.getOrderId());
        if (existing.isEmpty()) {
            Customer customer = customerRepository.findCustomersByLastNameOrFirstNameOrAccountNo(Order.from(requestBody, customerRepository).getCustomer().getAccountNo()).get(0);
            Order created = orderRepository.save(Order.from(requestBody, customerRepository));
            return new ResponseEntity<>(mapper.bo2dto(created), HttpStatus.CREATED);
        } else {
            throw new OrderAlreadyExistsException(requestBody.getOrderId());
        }
    }

    @PutMapping("/orders")
    ResponseEntity<OrderDto> updateOrders(@RequestBody OrderDto requestBody) {
        if (requestBody.getOrderId() == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        Optional<Order> existing = orderRepository.findById(requestBody.getOrderId());
        if (existing.isEmpty()) {
            throw new OrderNotFoundException(requestBody.getOrderId());
        } else {
            Order existingOrder = existing.get();
            OrderStatus orderStatus = orderStatusRepository.findById(requestBody.getOrderStatus().getOrderStatusId())
                    .orElseThrow(() -> new RuntimeException("Order status not found"));
            Order updatedOrder = existingOrder.updateFrom(requestBody, orderStatus);
            orderRepository.save(updatedOrder);
            return ResponseEntity.ok().body(mapper.bo2dto(updatedOrder));
        }
    }

    @DeleteMapping("/orders")
    ResponseEntity<Void> deleteOrders(@PathVariable String id) {
        Optional<Order> existing = orderRepository.findById(id);
        orderRepository.delete(existing.orElseThrow(() -> new OrderNotFoundException(id)));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/{accountNo}")
    public ResponseEntity<List<OrderDto>> getOrderDetails(@PathVariable String accountNo) {
        List<OrderDto> existing = orderRepository.findOrdersByCustomer(accountNo).stream().map(mapper::bo2dto).collect(Collectors.toList());
        return ResponseEntity.ok(existing);
    }

    @GetMapping("/orders/{accountNo}/{orderId}")
    public ResponseEntity<List<OrderDto>> getOrdersFromCustomerByOrderId(@PathVariable String accountNo, @PathVariable String orderId) {
        List<OrderDto> existing = orderRepository.findOrdersByCustomerAndOrderId(accountNo, orderId).stream().map(mapper::bo2dto).collect(Collectors.toList());
        return ResponseEntity.ok(existing);
    }
}