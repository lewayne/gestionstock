package com.lewayne.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Builder
@Data
public class LigneCommandeFournisseurDTO {
    private Integer id;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    private CommandeFournisseurDTO commandeFournisseur;

    private ArticleDTO article;
}
