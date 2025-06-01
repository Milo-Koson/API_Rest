package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends ResponseException {

    public ProductAlreadyExistsException(String id) {
        super(HttpStatus.CONFLICT, String.format("L'objet Product [id=%s] existe déjà", id));
    }
}