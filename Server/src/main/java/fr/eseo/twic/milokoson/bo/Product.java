package fr.eseo.twic.milokoson.bo;

import fr.eseo.twic.milokoson.dto.Product.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_no")
    private String productNo;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private float price;

    public static Product from(ProductDto dto) {
        return new Product(dto.getProductId(), dto.getProductNo(), dto.getProductName(), dto.getPrice());
    }

}
