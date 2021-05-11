package com.lewayne.gestiondestock.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvtstock")
public class SourceMvtStock extends AbstractEntity {

    /*@Column(name = "datemvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "typemvt")
    @Enumerated(EnumType.STRING)
    private TypeMvtStock typeMvt;

    @Column(name = "sourcemvt")
    @Enumerated(EnumType.STRING)
    private SourceMvtStock sourceMvt;

    @Column(name = "identreprise")
    private Integer idEntreprise;*/
}
