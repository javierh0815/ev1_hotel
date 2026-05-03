package com.hotel.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaNotFounException extends RuntimeException {
    public ReservaNotFounException(Long id) {
        super("Reserva no encontrada con ID: " + id);
    }

    
}
