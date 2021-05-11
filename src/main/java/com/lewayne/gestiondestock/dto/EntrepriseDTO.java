package com.lewayne.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lewayne.gestiondestock.model.Entreprise;
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

    @JsonIgnore
    private List<UtilisateurDTO> utilisateurs;

    public static EntrepriseDTO fromEntity(Entreprise entreprise) {
        if (entreprise == null) {
            return null;
        }
        return EntrepriseDTO.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .adresse(AdresseDTO.fromEntity(entreprise.getAdresse()))
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .steWeb(entreprise.getSteWeb())
                .build();
    }

    public static Entreprise toEntity(EntrepriseDTO dto) {
        if (dto == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getId());
        entreprise.setNom(dto.getNom());
        entreprise.setDescription(dto.getDescription());
        entreprise.setAdresse(AdresseDTO.toEntity(dto.getAdresse()));
        entreprise.setCodeFiscal(dto.getCodeFiscal());
        entreprise.setPhoto(dto.getPhoto());
        entreprise.setEmail(dto.getEmail());
        entreprise.setNumTel(dto.getNumTel());
        entreprise.setSteWeb(dto.getSteWeb());

        return entreprise;
    }

}
