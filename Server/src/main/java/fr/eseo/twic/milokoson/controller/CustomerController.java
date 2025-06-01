package fr.eseo.twic.milokoson.controller;

import fr.eseo.twic.milokoson.bo.Customer;
import fr.eseo.twic.milokoson.dao.*;
import fr.eseo.twic.milokoson.dto.Customer.CustomerDto;
import fr.eseo.twic.milokoson.dto.Customer.CustomerMapper;
import fr.eseo.twic.milokoson.exception.CustomerAlreadyExistsException;
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
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;

    LocalDate localDateToday = LocalDate.now();
    Date today = Date.from(localDateToday.atStartOfDay(ZoneId.systemDefault()).toInstant());
    String dateString = "9999-12-31";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    LocalDate localDateFuture = LocalDate.parse(dateString);
    LocalDate localDateYesterday = LocalDate.now().minusDays(1);
    Date yesterday = Date.from(localDateYesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());

    public CustomerController() throws ParseException {
    }

    @GetMapping("/customers")
    public ResponseEntity<Iterable<CustomerDto>> readCustomers() {
        return ResponseEntity.ok(mapper.bo2dtoList(repository.findAll()));
    }

    @PostMapping("/customers")
    ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto requestBody){
        Optional<Customer> existing = repository.findById(requestBody.getId());
        if(existing.isEmpty()){
            Customer created = repository.save(Customer.from(requestBody));
            return new ResponseEntity<>(mapper.bo2dto(created), HttpStatus.CREATED);
        } else {
            throw new CustomerAlreadyExistsException(requestBody.getId());
        }
    }

    @GetMapping("/search/{accountNo}")
    public ResponseEntity<List<CustomerDto>> searchCustomers(@PathVariable String accountNo) {
        List<CustomerDto> existing = repository.findCustomersByLastNameOrFirstNameOrAccountNo(accountNo).stream().map(mapper::bo2dto).collect(Collectors.toList());
        return ResponseEntity.ok(existing);
    }

    @GetMapping("/customers/{accountNo}")
    public ResponseEntity<CustomerDto> getCustomerDetails(@PathVariable String accountNo) {
        List<CustomerDto> existing = repository.findCustomersByLastNameOrFirstNameOrAccountNo(accountNo).stream().map(mapper::bo2dto).collect(Collectors.toList());
        return ResponseEntity.ok(existing.get(0));
    }
}