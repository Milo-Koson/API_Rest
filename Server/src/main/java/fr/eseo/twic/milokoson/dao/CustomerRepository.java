package fr.eseo.twic.milokoson.dao;

import fr.eseo.twic.milokoson.bo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c WHERE LOWER(c.lastName) LIKE LOWER(CONCAT('%', :identifier, '%')) OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :identifier, '%')) OR c.accountNo = :identifier")
    List<Customer> findCustomersByLastNameOrFirstNameOrAccountNo(@Param("identifier") String identifier);

}
