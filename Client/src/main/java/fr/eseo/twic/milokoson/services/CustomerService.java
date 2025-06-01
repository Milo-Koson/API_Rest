package fr.eseo.twic.milokoson.services;

import fr.eseo.twic.milokoson.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDto> searchCustomer(String searchString);

    CustomerDto getCustomerByAccountNo(String accountNo);

}
