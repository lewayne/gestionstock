package com.lewayne.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lewayne.gestiondestock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RolesDTO {
    private Integer id;

    private String roleName;

    @JsonIgnore
    private UtilisateurDTO utilisateur;

    public static RolesDTO fromEntity(Roles roles) {
        if (roles == null) {
            return null;
        }
        return RolesDTO.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();
    }

    public static Roles toEntity(RolesDTO dto) {
        if (dto == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setId(dto.getId());
        roles.setRoleName(dto.getRoleName());
        roles.setUtilisateur(UtilisateurDTO.toEntity(dto.getUtilisateur()));
        return roles;
    }
}
