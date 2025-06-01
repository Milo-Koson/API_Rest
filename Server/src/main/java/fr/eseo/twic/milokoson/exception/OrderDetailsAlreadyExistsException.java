package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class OrderDetailsAlreadyExistsException extends ResponseException{

    public OrderDetailsAlreadyExistsException(String id) {
        super(HttpStatus.CONFLICT, String.format("L'objet OrderDetails [id=%s] existe déjà", id));
    }

}
