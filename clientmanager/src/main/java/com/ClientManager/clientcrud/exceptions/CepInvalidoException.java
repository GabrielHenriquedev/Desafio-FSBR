package com.ClientManager.clientcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CEP inválido ou não encontrado")
public class CepInvalidoException extends RuntimeException {

    public CepInvalidoException(String message) {
        super(message);
    }
}