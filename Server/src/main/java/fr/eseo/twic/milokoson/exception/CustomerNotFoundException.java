package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ResponseException {

    public CustomerNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, String.format("L'objet Customer [id=%s] est introuvable", id));
    }
}