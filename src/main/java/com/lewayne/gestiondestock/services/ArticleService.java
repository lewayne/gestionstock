package com.lewayne.gestiondestock.services;

//Exemple : JPA est la spécification et hibernate est l'implementation
// Jpa défini des méthodes et n'importe quel framework comme Hibernate peut implementer ces méthodes

// une interface ici est un contrat de service

import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.dto.LigneCommandeClientDTO;
import com.lewayne.gestiondestock.dto.LigneCommandeFournisseurDTO;

import java.util.List;

public interface ArticleService {
    ArticleDTO save(ArticleDTO dto);

    ArticleDTO findById(Integer id);

    ArticleDTO findByCodeArticle(String codeArticle);

    List<ArticleDTO> findAll();

    /*List<ArticleDTO> findHistoriqueVentes(Integer idArticle);

    List<LigneCommandeClientDTO> findHistoriaueCommandeClient(Integer idArticle);

    List<LigneCommandeFournisseurDTO> findHistoriqueCommandeFournisseur(Integer idArticle);

    List<ArticleDTO> findAllArticleByIdCategory(Integer idCategory);*/

    void delete(Integer id);
}
