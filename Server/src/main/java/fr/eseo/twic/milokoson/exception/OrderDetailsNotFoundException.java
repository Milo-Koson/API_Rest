package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class OrderDetailsNotFoundException extends ResponseException{

    public OrderDetailsNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, String.format("L'objet OrderDetails [id=%s] est introuvable", id));
    }
}
