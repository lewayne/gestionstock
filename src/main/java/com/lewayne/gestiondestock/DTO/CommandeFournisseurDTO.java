package com.lewayne.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeFournisseurDTO {
    private Integer id;

    private String code;

    private Instant dateCommande;

    /*@Column(name = "etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;*/

    private Integer idEntreprise;

    private FournisseurDTO fournisseur;

    private List<LigneCommandeFournisseurDTO> ligneCommandeFournisseurs;

}
