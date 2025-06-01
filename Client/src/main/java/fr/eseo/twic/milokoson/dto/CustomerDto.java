package fr.eseo.twic.milokoson.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto extends RepresentationModel<CustomerDto> {

    public String lastName;
    public String firstName;
    public String accountNo;
    public String id;
    public LocalDate registrationTimestamp;
    public String email;

}
