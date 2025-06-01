package fr.eseo.twic.milokoson.exception;

import org.springframework.http.HttpStatus;

public class MismatchedBodyTargetException extends ResponseException {

    public MismatchedBodyTargetException(String bodyId, String targetId) {
        super(HttpStatus.BAD_REQUEST, String.format("L'id du contenu [%s] ne correspond pas à la cible [%s]", bodyId, targetId));
    }
}