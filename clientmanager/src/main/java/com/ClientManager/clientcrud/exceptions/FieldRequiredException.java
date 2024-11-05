package com.ClientManager.clientcrud.exceptions;

public class FieldRequiredException extends RuntimeException  {

    public FieldRequiredException(String fieldName) {
        super("O campo '" + fieldName + "' não pode estar vazio.");
    }
}

