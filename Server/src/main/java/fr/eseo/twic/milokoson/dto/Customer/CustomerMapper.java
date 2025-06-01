package fr.eseo.twic.milokoson.dto.Customer;
import fr.eseo.twic.milokoson.bo.Customer;

public interface CustomerMapper {

    CustomerDto bo2dto(Customer bo);

    Iterable<CustomerDto> bo2dtoList(Iterable<Customer> bo);

}