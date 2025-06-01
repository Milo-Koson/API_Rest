package fr.eseo.twic.milokoson.controller;

import fr.eseo.twic.milokoson.bo.Product;
import fr.eseo.twic.milokoson.dao.ProductRepository;
import fr.eseo.twic.milokoson.dto.Product.ProductDto;
import fr.eseo.twic.milokoson.dto.Product.ProductMapper;
import fr.eseo.twic.milokoson.exception.ProductAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/v{apiversion}")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    LocalDate localDateToday = LocalDate.now();
    Date today = Date.from(localDateToday.atStartOfDay(ZoneId.systemDefault()).toInstant());
    String dateString = "9999-12-31";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    LocalDate localDateFuture = LocalDate.parse(dateString);
    LocalDate localDateYesterday = LocalDate.now().minusDays(1);
    Date yesterday = Date.from(localDateYesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());

    public ProductController() throws ParseException {
    }

    @GetMapping("/products")
    public ResponseEntity<Iterable<ProductDto>> readProducts() {
        return ResponseEntity.ok(mapper.bo2dtoList(repository.findAll()));
    }

    @PostMapping("/products")
    ResponseEntity<ProductDto> createProducts(@RequestBody ProductDto requestBody){
        Optional<Product> existing = repository.findById(requestBody.getProductId());
        if(existing.isEmpty()){
            Product created = repository.save(Product.from(requestBody));
            return new ResponseEntity<>(mapper.bo2dto(created), HttpStatus.CREATED);
        } else {
            throw new ProductAlreadyExistsException(requestBody.getProductId());
        }
    }

}