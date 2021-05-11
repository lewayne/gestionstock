package com.lewayne.gestiondestock.dto;

import com.lewayne.gestiondestock.model.CommandeFournisseur;
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

    public static CommandeFournisseurDTO fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null) {
            return null;
        }
        return CommandeFournisseurDTO.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(FournisseurDTO.fromEntity(commandeFournisseur.getFournisseur()))
                //.etatCommande(commandeFournisseur.getEtatCommande())
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDTO dto) {
        if (dto == null) {
            return null;
        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(dto.getId());
        commandeFournisseur.setCode(dto.getCode());
        commandeFournisseur.setDateCommande(dto.getDateCommande());
        commandeFournisseur.setFournisseur(FournisseurDTO.toEntity(dto.getFournisseur()));
        commandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        //commandeFournisseur.setEtatCommande(dto.getEtatCommande());
        return commandeFournisseur;
    }

    /*public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }*/

}
