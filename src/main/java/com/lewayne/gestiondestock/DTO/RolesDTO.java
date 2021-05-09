package com.lewayne.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RolesDTO {
    private Integer id;

    private String roleName;

    private UtilisateurDTO utilisateur;
}
