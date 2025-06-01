package fr.eseo.twic.milokoson.bo;

import fr.eseo.twic.milokoson.dto.Customer.CustomerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_id")
    private String id;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "registration_timestamp")
    private LocalDateTime registrationTimestamp;

    @Column(name = "email")
    private String email;

    public Customer(String lastName, String firstName, String accountNo) {

    }

    public static Customer from(CustomerDto dto) {
        return new Customer(dto.getLastName(), dto.getFirstName(), dto.getAccountNo());
    }

}