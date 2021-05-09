package com.lewayne.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class UtilisateurDTO {
    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String moteDePasse;

    private AdresseDTO adresse;

    private String photo;

    private EntrepriseDTO entreprise;

    private List<RolesDTO> roles;
}
