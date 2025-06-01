package fr.eseo.twic.milokoson.viewmodel;

import fr.eseo.twic.milokoson.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentViewModel {
    private List<CustomerDto> customerDtoList;
}