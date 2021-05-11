package com.lewayne.gestiondestock.dto;

import com.lewayne.gestiondestock.model.MvtStock;
import com.lewayne.gestiondestock.model.SourceMvtStock;
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


    private SourceMvtStock sourceMvt;

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

    public static MvtStockDTO fromEntity(MvtStock mvtStk) {
        if (mvtStk == null) {
            return null;
        }

        return MvtStockDTO.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .article(ArticleDTO.fromEntity(mvtStk.getArticle()))
                .typeMvtStock(mvtStk.getTypeMvtStock())
                // .sourceMvt(mvtStk.getSourceMvt())  //.getSourceMvt()
                .idEntreprise(mvtStk.getIdEntreprise())
                .build();
    }

    public static MvtStock toEntity(MvtStockDTO dto) {
        if (dto == null) {
            return null;
        }

        MvtStock mvtStk = new MvtStock();
        mvtStk.setId(dto.getId());
        mvtStk.setDateMvt(dto.getDateMvt());
        mvtStk.setQuantite(dto.getQuantite());
        mvtStk.setArticle(ArticleDTO.toEntity(dto.getArticle()));
        //mvtStk.setTypeMvt(dto.getTypeMvt());
        //mvtStk.setSourceMvt(dto.getSourceMvt());
        mvtStk.setIdEntreprise(dto.getIdEntreprise());
        return mvtStk;
    }
}
