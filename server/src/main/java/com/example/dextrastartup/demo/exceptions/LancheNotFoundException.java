package com.example.dextrastartup.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LancheNotFoundException extends RuntimeException {

    public LancheNotFoundException(Integer id, Class<?> typeClass) {
        super("Lanche com código: " + id.toString() + " não existe. Tente novamente");
    }
}


