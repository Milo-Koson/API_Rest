package fr.eseo.twic.milokoson.dao;

import fr.eseo.twic.milokoson.bo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
