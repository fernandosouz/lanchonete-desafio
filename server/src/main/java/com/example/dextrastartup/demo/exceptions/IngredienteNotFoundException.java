package com.example.dextrastartup.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredienteNotFoundException extends RuntimeException {

    public IngredienteNotFoundException(Integer id, Class<?> typeClass) {
        super("Ingrediente com código: " + id.toString() + " não existe. Tente novamente");
    }
}