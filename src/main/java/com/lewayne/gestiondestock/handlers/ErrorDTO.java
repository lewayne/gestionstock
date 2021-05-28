package com.lewayne.gestiondestock.handlers;

import com.lewayne.gestiondestock.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//Gestion d'exception globale :  c'est un écouteur ou un listener qui va écouter sur toute l'application et à chaque fois on lève une exception ou une exception se lève
// dans l'application il va l'intercepter et on peut ajouter le traitement qu'on veut faire


// C'est l'objet qu'on renvoie lorsqu'on trouve ou catch une exception
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorDTO {

    private Integer httpCode; //Pour le code http -- par exemple 404;

    private ErrorCodes code;  // pour nos codes d' ErrorCodes  --- ENUM

    private String message;  // message

    private List<String> errors = new ArrayList<>();  // liste d'érreurs renvoyée par les validateurs
                    // par exemple quand t-on lève l'exception InvalidEntityException je veux pouvoir récupérer ces codes (cette liste) érreurs et les renvoyés
}
