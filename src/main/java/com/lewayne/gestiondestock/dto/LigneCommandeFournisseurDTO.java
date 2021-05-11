package com.lewayne.gestiondestock.dto;

import com.lewayne.gestiondestock.model.LigneCommandeFournisseur;
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

    public static LigneCommandeFournisseurDTO fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (ligneCommandeFournisseur == null) {
            return null;
        }
        return LigneCommandeFournisseurDTO.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleDTO.fromEntity(ligneCommandeFournisseur.getArticle()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDTO dto) {
        if (dto == null) {
            return null;
        }

        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(dto.getId());
        ligneCommandeFournisseur.setArticle(ArticleDTO.toEntity(dto.getArticle()));
        ligneCommandeFournisseur.setPrixUnitaire(dto.getPrixUnitaire());
        ligneCommandeFournisseur.setQuantite(dto.getQuantite());
        ligneCommandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        return ligneCommandeFournisseur;
    }
}
