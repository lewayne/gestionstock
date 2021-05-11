package com.lewayne.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lewayne.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ArticleDTO {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategorieDTO category;

    private Integer idEntreprise;

    public static  ArticleDTO fromEntity(Article article){
        if(article == null){
            return null;
        }

        return ArticleDTO.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .build();
    }


    public static Article toEntity(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setCodeArticle(articleDTO.getCodeArticle());
        article.setDesignation(articleDTO.getDesignation());
        article.setPhoto(articleDTO.getPhoto());
        article.setPrixUnitaireHt(articleDTO.getPrixUnitaireHt());
        article.setPrixUnitaireTtc(articleDTO.getPrixUnitaireTtc());
        article.setTauxTva(articleDTO.getTauxTva());

        article.setIdEntreprise(articleDTO.getIdEntreprise());
        article.setCategory(CategorieDTO.toEntity(articleDTO.getCategory()));
        return article;
    }
}
