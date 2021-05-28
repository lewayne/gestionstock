package com.lewayne.gestiondestock.exception;

import lombok.Getter;

//Exception personnalisée : Permet d'implementer ou bien lorsqu'on renvoi quelque chose qui ne va pas côté API ça va simplifier ou clarifier
// c'est quoi exacetement le problème


// cet exception concerne les cherches qu'on peut éffectuer sur une entité
// On leve une de ces exceptions si on ne trouve pas un élement dans la base de données
public class EntityNotFoundException extends RuntimeException {

    @Getter
    private ErrorCodes errorCode; // enum

    // on lève l'exception avec juste un message d'erreur
    public EntityNotFoundException(String message) {
        super(message);
    }

    // on lève l'exception avec juste un message d'erreur et la cause
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode; // A partir du code d'érreur on peut avoir le nom de l'érreur ??
        //this.errorCode(errorCode); this.errorCode.setCode(errorCode)  ?????
    }

    public EntityNotFoundException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
