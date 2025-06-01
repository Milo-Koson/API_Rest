package fr.eseo.twic.milokoson.dto.Customer;

import fr.eseo.twic.milokoson.bo.Customer;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SimpleCustomerMapper implements CustomerMapper {

    @Override
    public CustomerDto bo2dto(Customer bo) {
        return new CustomerDto(bo.getId(), bo.getAccountNo(), bo.getFirstName(),bo.getLastName(), bo.getRegistrationTimestamp(), bo.getEmail());
    }

    @Override
    public Iterable<CustomerDto> bo2dtoList(Iterable<Customer> bo) {
        return StreamSupport.stream(bo.spliterator(), false)
                .map(this::bo2dto).collect(Collectors.toList());
    }

}