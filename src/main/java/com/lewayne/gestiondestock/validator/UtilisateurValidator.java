package com.lewayne.gestiondestock.validator;

import com.lewayne.gestiondestock.dto.CategorieDTO;
import com.lewayne.gestiondestock.dto.UtilisateurDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDTO utilisateurDTO){
        List<String> errors = new ArrayList<>();

        if (utilisateurDTO == null) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le prenom d'utilisateur");
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
            errors.add("Veuillez renseigner l'adresse d'utilisateur");
            errors.addAll(AdressseValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(utilisateurDTO.getNom())) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getPrenom())) {
            errors.add("Veuillez renseigner le prenom d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getEmail())) {
            errors.add("Veuillez renseigner l'email d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getMoteDePasse())) {
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
        }
        if (utilisateurDTO.getDateDeNaissance() == null) {
            errors.add("Veuillez renseigner la date de naissance d'utilisateur");
        }
        errors.addAll(AdressseValidator.validate(utilisateurDTO.getAdresse()));

        return errors;
    }
}
