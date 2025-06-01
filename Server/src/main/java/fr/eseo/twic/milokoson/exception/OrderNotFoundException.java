package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends ResponseException {

    public OrderNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, String.format("L'objet Order [id=%s] est introuvable", id));
    }
}