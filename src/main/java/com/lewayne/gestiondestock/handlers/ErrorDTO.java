package com.lewayne.gestiondestock.handlers;

import com.lewayne.gestiondestock.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// C'est l'objet qu'on renvoie lorsqu'on trouve ou catch une exception
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorDTO {

    private Integer httpCode; //Pour le code http -- 404;

    private ErrorCodes code;  // pour le code ErrorCodes

    private String message;

    private List<String> errors = new ArrayList<>();
}
