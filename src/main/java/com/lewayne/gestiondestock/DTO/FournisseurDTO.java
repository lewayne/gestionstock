package com.lewayne.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FournisseurDTO {
    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDTO adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    private List<CommandeFournisseurDTO> commandeFournisseurs;
}
