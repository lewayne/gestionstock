package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.Article;
import com.lewayne.gestiondestock.repository.ArticleRepository;
import com.lewayne.gestiondestock.services.ArticleService;
import com.lewayne.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Service("") on peut donner un nom lorqu'on défini une deuxième implementation
@Service("articleServiceImpl")
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository; // on crée une instance de notre repository

    @Autowired //On injecte l'ArticleRepository en faisant une injection par constructeur
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public ArticleDTO save(ArticleDTO dto) {
        List<String> errors = ArticleValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}", dto);
            throw  new InvalidEntityException("L'article n'est pas valide",  ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        Article savedArticle = articleRepository.save(ArticleDTO.toEntity(dto));
        return ArticleDTO.fromEntity(savedArticle);

        /*return ArticleDTO.fromEntity(
                articleRepository.save(
                        ArticleDTO.toEntity(dto)
                )
        );*/
    }

    @Override
    public ArticleDTO findById(Integer id) {
        if(id == null){
            log.error("Article ID is null");
            return  null;
        }
        Optional<Article> article = articleRepository.findById(id);

        ArticleDTO dto = ArticleDTO.fromEntity(article.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "aucun article avec l'ID = "  + id + " n'a été trouvé dans la base de données", ErrorCodes.ARTICLE_NOT_FOUND
                )
        );
    }

    @Override
    public ArticleDTO findByCodeArticle(String codeArticle) {
        if(!StringUtils.hasLength((codeArticle))){
            log.error("Article CODE is null");
            return  null;
        }

        return articleRepository.findArticleByCodeArticle(codeArticle)
                .map(ArticleDTO::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException(
                                "aucun article avec le code = "  + codeArticle + " n'a été trouvé dans la base de données", ErrorCodes.ARTICLE_NOT_FOUND
                        )
                );

        /*Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);

        ArticleDTO dto = ArticleDTO.fromEntity(article.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "aucun article avec le code = "  + codeArticle + " n'a été trouvé dans la base de données", ErrorCodes.ARTICLE_NOT_FOUND
                )
        );*/
    }

    @Override
    public List<ArticleDTO> findAll() {
        return articleRepository.findAll().stream()
                    .map(ArticleDTO::fromEntity)  //mapping, on map d'un objet à un autre
                    .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) { //Article article
        if(id == null){
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);
        //articleRepository.deleteAll();
    }
}
