package fr.eseo.twic.milokoson.dto.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto extends RepresentationModel<CustomerDto> {

    private String id;
    private String accountNo;
    private String firstName;
    private String lastName;
    private LocalDateTime registrationTimestamp;
    private String email;

}