package com.example.dextrastartup.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PromocaoNotFoundException extends RuntimeException {

    public PromocaoNotFoundException(Integer id, Class<?> typeClass) {
        super("Promoção com código: " + id.toString() + " não existe. Tente novamente");
    }

}
