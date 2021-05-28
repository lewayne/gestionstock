package com.lewayne.gestiondestock.controller;


import com.lewayne.gestiondestock.controller.api.ArticleApi;
import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {
    // Getter injection : on crée une méthode get pour un type service et on met l'annotation au niveau du get
    /*  @Autowired
        public ArticleServices getArticlesServices(){
            return articleService
        }
     */

    // field injection : injecté lorqu'on utilise cet objet articleService
    // @Autowired
    @Qualifier("articleServiceImpl") //avec les services une interface peut avoir plusieurs implementations
    private ArticleService articleService;

    // est ce qu'on a besoin de faire un autowired sur le constructeur ???
    /* lors de la création de cette classe ArticleController Spring va injecter automatiquement toutes les dépendences qui se sont
        declarées dans le constructeur
     */
    // constructor injection
    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @Override
    public ArticleDTO save(ArticleDTO dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDTO findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDTO findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDTO> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }
}
