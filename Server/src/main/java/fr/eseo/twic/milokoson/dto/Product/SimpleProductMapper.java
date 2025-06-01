package fr.eseo.twic.milokoson.dto.Product;

import fr.eseo.twic.milokoson.bo.Product;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SimpleProductMapper implements ProductMapper {

    @Override
    public ProductDto bo2dto(Product bo) {
        return new ProductDto(bo.getProductId(), bo.getProductNo(), bo.getProductName(), bo.getPrice());
    }

    @Override
    public Iterable<ProductDto> bo2dtoList(Iterable<Product> bo) {
        return StreamSupport.stream(bo.spliterator(), false)
                .map(this::bo2dto).collect(Collectors.toList());
    }

}