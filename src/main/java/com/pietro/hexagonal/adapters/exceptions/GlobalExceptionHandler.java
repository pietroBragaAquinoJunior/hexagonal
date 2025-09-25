package com.pietro.hexagonal.adapters.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.pietro.hexagonal.core.domain.exceptions.RecursoNaoEncontradoException;

// Esse handler com MethodArgumentNotValidException é necessário para pegar as mensagens do @Valid (validator)
// No RequestDto para a mensagem aparecer de forma mais amigável ao cliente. Uso o ApiError para aparecer muito mais que somente a mensagem.

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            details.put(error.getField(), error.getDefaultMessage()));

        ApiError apiError = new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "Campos inválidos na requisição.",
            request.getDescription(false).substring(4),
            details
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // Tratar uma exceção customizada que deverá ser lançada pelo DOMÍNIO.
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
