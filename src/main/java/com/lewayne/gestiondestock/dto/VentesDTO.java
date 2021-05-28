package com.lewayne.gestiondestock.dto;

import com.lewayne.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;


import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDTO {
    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDTO> ligneVentes;

    public static VentesDTO fromEntity(Ventes vente) {
        if (vente == null) {
            return null;
        }
        return VentesDTO.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .commentaire(vente.getCommentaire())
                .idEntreprise(vente.getIdEntreprise())
                //.ligneVentes(vente.getLigneVentes()) on ne map pas ici parceque si on besoin de checher les ventes alors on va faire un appel separé
                .build();
    }

    public static Ventes toEntity(VentesDTO dto) {
        if (dto == null) {
            return null;
        }
        Ventes ventes = new Ventes();
        ventes.setId(dto.getId());
        ventes.setCode(ventes.getCode());
        ventes.setCommentaire(dto.getCommentaire());
        ventes.setIdEntreprise(dto.getIdEntreprise());
        return ventes;
    }
}
