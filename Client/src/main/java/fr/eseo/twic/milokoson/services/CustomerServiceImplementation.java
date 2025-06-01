package fr.eseo.twic.milokoson.services;

import fr.eseo.twic.milokoson.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private final RestTemplate restTemplate;

    public CustomerServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CustomerDto> getOrders() {
        String url = "http://localhost:8080/v1.0/customers";

        ResponseEntity<List<CustomerDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CustomerDto>>() {}
        );

        return responseEntity.getBody();
    }

    @Override
    public List<CustomerDto> searchCustomer(String searchString) {
        String url = "http://localhost:8080/v1.0/search/" + searchString;
        CustomerDto[] ordersArray = restTemplate.getForObject(url, CustomerDto[].class);
        assert ordersArray != null;
        return Arrays.asList(ordersArray);
    }

    @Override
    public CustomerDto getCustomerByAccountNo(String accountNo){
        String url = "http://localhost:8080/v1.0/customers/" + accountNo;
        try {
            ResponseEntity<CustomerDto> responseEntity = restTemplate.getForEntity(url, CustomerDto.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            return new CustomerDto(null, null, null, null, null, null);
        }
    }

}
