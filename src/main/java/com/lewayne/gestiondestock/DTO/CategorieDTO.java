package com.lewayne.gestiondestock.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lewayne.gestiondestock.model.Categorie;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategorieDTO {
    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDTO> articles;

    //category --- DTO
    public static CategorieDTO fromEntity(Categorie categorie) {
        if (categorie == null) {
            return null; // throw an exception
        }
        return CategorieDTO.builder()
                    .id(categorie.getId())
                    .code(categorie.getCode())
                    .designation(categorie.getDesignation())
                    .build();
    }

}
