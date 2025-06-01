package fr.eseo.twic.milokoson.services;

import fr.eseo.twic.milokoson.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<OrderDto> getOrdersByAccountNo(String accountNo);

}