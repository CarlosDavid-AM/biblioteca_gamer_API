package com.biblioteca.gemer.Enums;

import org.springframework.http.HttpStatus;

public enum APIError {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Los atributos con valores incorrectos"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "El mensaje no tiene el formato correcto."),
    GAME_NOT_FOUND(HttpStatus.NOT_FOUND, "Juego no encontrado"),
    GAME_WITH_SAME_ID(HttpStatus.BAD_REQUEST, "Existe un juego con el mismo ID.");

    private final HttpStatus httpStatus;
    private final String message;

    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
