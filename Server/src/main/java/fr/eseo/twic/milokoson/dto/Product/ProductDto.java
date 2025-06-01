package fr.eseo.twic.milokoson.dto.Product;

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
    private String productNo;
    private String productName;
    private float price;
    
}