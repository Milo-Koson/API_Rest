package fr.eseo.twic.milokoson.dto;

import fr.eseo.twic.milokoson.exception.ResponseException;

public interface ErrorMapper {

    ErrorDto bo2dto(ResponseException bo);
}