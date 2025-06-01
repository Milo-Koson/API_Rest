package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class OrderStatusNotFoundException extends ResponseException{

    public OrderStatusNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, String.format("L'objet Order Status [id=%s] est introuvable", id));
    }

}
