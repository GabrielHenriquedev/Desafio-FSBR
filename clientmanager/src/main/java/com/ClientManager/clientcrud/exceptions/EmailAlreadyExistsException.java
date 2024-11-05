package com.ClientManager.clientcrud.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistsException(String email) {
        super("O email '" + email + "' já está cadastrado.");
    }
}
