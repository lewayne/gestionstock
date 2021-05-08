package com.lewayne.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ligneCommandeClient")
public class LigneCommandeClient extends AbstractEntity {

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne()
    @JoinColumn(name = "idComnadeClient")
    private CommandeClient commandeClient;

    @ManyToOne()
    @JoinColumn(name = "idArticle")
    private Article article;














}
