package com.pietro.hexagonal.core.domain.exceptions;


public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}
