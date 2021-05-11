package com.lewayne.gestiondestock.exception;

import lombok.Getter;


// cet exception concerne les cherches qu'on peut éffectuer sur une entité
// On leve cet exception si on ne trouve pas un élement dans la base de données
public class EntityNotFoundException extends RuntimeException {

    @Getter
    private ErrorCodes errorCode;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public EntityNotFoundException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
