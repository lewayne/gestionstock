package com.lewayne.gestiondestock.exception;

import lombok.Getter;

import java.util.List;

//Exception personnalisée : Permet d'implementer ou bien lorsqu'on renvoi quelque chose qui ne va pas côté API ça va simplifier ou clarifier
// c'est quoi exacetement le problème


// cet exeception est levé si on essaye d'enregistrer quelque chose dans la base de données
// et lorqu'on passe par la validation cet entité (ou exception ????) n'est pas valide
public class InvalidEntityException extends RuntimeException {

    @Getter
    private ErrorCodes errorCode;  // enum

    @Getter
    private List<String> errors;  // liste d'érreurs renvoyée par les validateurs

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public InvalidEntityException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public InvalidEntityException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }

}
