package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistsException extends ResponseException {

    public CustomerAlreadyExistsException(String id) {
        super(HttpStatus.CONFLICT, String.format("L'objet Customer [id=%s] existe déjà", id));
    }
}