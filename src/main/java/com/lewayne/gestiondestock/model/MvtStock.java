package com.lewayne.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="mvtStock")
public class MvtStock extends AbstractEntity{

    @Column(name = "datemvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "typemvtstock")
    private TypeMvtStock typeMvtStock;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    /*@Column(name = "typemvt")
    @Enumerated(EnumType.STRING)
    private TypeMvtStk typeMvt;

    @Column(name = "sourcemvt")
    @Enumerated(EnumType.STRING)
    private SourceMvtStk sourceMvt;*/

    @Column(name = "identreprise")
    private Integer idEntreprise;

    /*@ManyToOne()
    @JoinColumn(name="idarticle")
    private Article article;*/
}
