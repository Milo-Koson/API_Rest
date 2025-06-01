package fr.eseo.twic.milokoson.dto.Product;

import fr.eseo.twic.milokoson.bo.Product;

public interface ProductMapper {

    ProductDto bo2dto(Product bo);

    Iterable<ProductDto> bo2dtoList(Iterable<Product> bo);

}