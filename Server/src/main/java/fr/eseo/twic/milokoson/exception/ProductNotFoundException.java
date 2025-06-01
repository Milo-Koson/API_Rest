package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ResponseException {

    public ProductNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, String.format("L'objet Product [id=%s] est introuvable", id));
    }
}