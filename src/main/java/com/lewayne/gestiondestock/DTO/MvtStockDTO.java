package com.lewayne.gestiondestock.DTO;

import com.lewayne.gestiondestock.model.TypeMvtStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MvtStockDTO {
    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private TypeMvtStock typeMvtStock;

    private ArticleDTO article;

    /*@Column(name = "typemvt")
    @Enumerated(EnumType.STRING)
    private TypeMvtStk typeMvt;

    @Column(name = "sourcemvt")
    @Enumerated(EnumType.STRING)
    private SourceMvtStk sourceMvt;*/

    private Integer idEntreprise;

    /*@ManyToOne()
    @JoinColumn(name="idarticle")
    private Article article;*/
}
