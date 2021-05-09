package com.lewayne.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdresseDTO {
    private String adresse1;

    private String adresse2;

    private String ville;

    private String codePostale;

    private String pays;
}
