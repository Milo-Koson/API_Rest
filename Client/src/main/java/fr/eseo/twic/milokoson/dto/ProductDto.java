package fr.eseo.twic.milokoson.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto extends RepresentationModel<ProductDto> {

    private String productId;
    private String productName;

}
