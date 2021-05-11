package com.lewayne.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ligneVente")
public class LigneVente extends AbstractEntity{

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne()
    @JoinColumn(name = "idVente")
    private Ventes vente;


    @ManyToOne()
    @JoinColumn(name = "idArticle")
    private Article article;
}
