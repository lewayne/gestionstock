package com.lewayne.gestiondestock.DTO;

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

    private List<LigneCommandeClientDTO> ligneCommandeClients;
}
