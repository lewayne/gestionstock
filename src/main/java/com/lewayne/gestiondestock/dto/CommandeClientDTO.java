package com.lewayne.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lewayne.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeClientDTO {
    private Integer id;

    private String code;

    private Instant dateCommande;

    /*@Column(name = "etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;*/

    private Integer idEntreprise;

    private ClientDTO client;

//@JsonIgnore
    private List<LigneCommandeClientDTO> ligneCommandeClients;

    public static CommandeClientDTO fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
        }
        return CommandeClientDTO.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                //.etatCommande(commandeClient.getEtatCommande())
                //.client(ClientDTO.fromEntity(commandeClient.getClient()))
                .idEntreprise(commandeClient.getIdEntreprise())
                .build();

    }

    public static CommandeClient toEntity(CommandeClientDTO dto) {
        if (dto == null) {
            return null;
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(dto.getId());
        commandeClient.setCode(dto.getCode());
        commandeClient.setClient(ClientDTO.toEntity(dto.getClient()));
        commandeClient.setDateCommande(dto.getDateCommande());
        //Pas de mapping de ligneCommandeClient

        //commandeClient.setEtatCommande(dto.getEtatCommande());
        commandeClient.setIdEntreprise(dto.getIdEntreprise());
        return commandeClient;
    }

    /*public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }*/
}
