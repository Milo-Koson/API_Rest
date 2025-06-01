package fr.eseo.twic.milokoson.services;

import fr.eseo.twic.milokoson.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private final RestTemplate restTemplate;

    public OrderServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrderDto> getOrders() {
        String url = "http://localhost:8080/v1.0/orders";

        ResponseEntity<List<OrderDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDto>>() {}
        );

        return responseEntity.getBody();
    }

    @Override
    public List<OrderDto> getOrdersByAccountNo(String accountNo) {
        String url = "http://localhost:8080/v1.0/orders/" + accountNo;
        OrderDto[] ordersArray = restTemplate.getForObject(url, OrderDto[].class);
        assert ordersArray != null;
        return Arrays.asList(ordersArray);
    }

}
