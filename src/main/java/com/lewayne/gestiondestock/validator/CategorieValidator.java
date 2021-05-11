package com.lewayne.gestiondestock.validator;
import com.lewayne.gestiondestock.dto.CategorieDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {

    public static List<String> validate(CategorieDTO categorieDTO){
        List<String> errors = new ArrayList<>();

        if(categorieDTO == null || !StringUtils.hasLength(categorieDTO.getCode())){
            errors.add("veuillez renseigner le code de la catégorie");
        }
        return errors;
    }
}
