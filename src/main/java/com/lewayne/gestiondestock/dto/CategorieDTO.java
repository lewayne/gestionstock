package com.lewayne.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lewayne.gestiondestock.model.Categorie;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorieDTO {
    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDTO> articles;

    //category --- DTO  // A partir d'une entité on construis notre dto
    public static CategorieDTO fromEntity(Categorie categorie) {
        if (categorie == null) {
            return null;
            // TODO throw an exception
        }

        return CategorieDTO.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .idEntreprise(categorie.getIdEntreprise())
                .build();
    }

    public static Categorie toEntity(CategorieDTO categoryDto) {
        if (categoryDto == null) {
            return null;
            // TODO throw an exception
        }

        Categorie category = new Categorie();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setIdEntreprise(categoryDto.getIdEntreprise());

        return category;
    }

}
