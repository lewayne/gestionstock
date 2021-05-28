package com.lewayne.gestiondestock.dto;

import com.lewayne.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeClientDTO {
    private Integer id;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    //private Integer idEntreprise;

    private CommandeClientDTO commandeClient;

    private ArticleDTO article;

    public static LigneCommandeClientDTO fromEntity(LigneCommandeClient ligneCommandeClient) {
        if (ligneCommandeClient == null) {
            return null;
        }
        return LigneCommandeClientDTO.builder()
                .id(ligneCommandeClient.getId())
                .article(ArticleDTO.fromEntity(ligneCommandeClient.getArticle()))
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                //.idEntreprise(ligneCommandeClient.getIdEntreprise())
                // Pas de mapping du client
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDTO dto) {
        if (dto == null) {
            return null;
        }

        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(dto.getId());
        ligneCommandeClient.setArticle(ArticleDTO.toEntity(dto.getArticle()));
        ligneCommandeClient.setPrixUnitaire(dto.getPrixUnitaire());
        ligneCommandeClient.setQuantite(dto.getQuantite());
        //ligneCommandeClient.setIdEntreprise(dto.getIdEntreprise());
        return ligneCommandeClient;
    }

}
