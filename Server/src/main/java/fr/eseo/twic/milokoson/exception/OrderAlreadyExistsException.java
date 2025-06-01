package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class OrderAlreadyExistsException extends ResponseException {

    public OrderAlreadyExistsException(String id) {
        super(HttpStatus.CONFLICT, String.format("L'objet Order [id=%s] existe déjà", id));
    }
}