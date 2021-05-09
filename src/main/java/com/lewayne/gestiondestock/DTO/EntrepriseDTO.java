package com.lewayne.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EntrepriseDTO {
    private Integer id;

    private String nom;

    private String description;

    private AdresseDTO adresse;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String steWeb;

    /*@OneToMany(mappedBy = "entreprise")
    private List<Article> articles;*/

    private List<UtilisateurDTO> utilisateurs;
}
