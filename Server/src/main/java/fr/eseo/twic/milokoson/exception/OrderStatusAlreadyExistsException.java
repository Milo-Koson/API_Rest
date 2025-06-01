package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class OrderStatusAlreadyExistsException extends ResponseException{

    public OrderStatusAlreadyExistsException(String id) {
        super(HttpStatus.CONFLICT, String.format("L'objet Order Status [id=%s] existe déjà", id));
    }

}
