package com.lewayne.gestiondestock.handlers;

import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleException(EntityNotFoundException exception, WebRequest webRequest) {
        // EntityNotFoundException : Exception lévée,
        // WebRequest : Requête qu'on va recevoir
        final HttpStatus notFound = HttpStatus.NOT_FOUND; // on construis un objet de type httpStatus, il va prendre le code NOT_FOUND (404)
        final ErrorDTO errorDto = ErrorDTO.builder()
                .code(exception.getErrorCode())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();  // on construis notre errorDTO

        return new ResponseEntity<>(errorDto, notFound);
    }


    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDTO> handleException(InvalidEntityException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDTO errorDto = ErrorDTO.builder()
                .code(exception.getErrorCode())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }
}
