package fr.eseo.twic.milokoson.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {

    protected final HttpStatus statusCode;
    @Getter
    protected final String details;

    public ResponseException(HttpStatus statusCode, String details) {
        super(details);
        this.statusCode = statusCode;
        this.details = details;
    }

    public int getCode() {
        return statusCode.value();
    }

    public String getMessage() {
        return statusCode.getReasonPhrase();
    }

}