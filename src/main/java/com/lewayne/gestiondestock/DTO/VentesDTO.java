package com.lewayne.gestiondestock.DTO;

import lombok.Builder;
import lombok.Data;


import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDTO {
    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDTO> ligneVentes;
}
