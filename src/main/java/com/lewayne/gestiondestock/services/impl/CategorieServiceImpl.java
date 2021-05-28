package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.CategorieDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.Categorie;
import com.lewayne.gestiondestock.repository.ArticleRepository;
import com.lewayne.gestiondestock.repository.CategorieRepository;
import com.lewayne.gestiondestock.services.CategorieService;
import com.lewayne.gestiondestock.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategorieServiceImpl implements CategorieService{

    private CategorieRepository categorieRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository, ArticleRepository articleRepository){
        this.categorieRepository = categorieRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CategorieDTO save(CategorieDTO categorieDTO) {
        List<String> errors = CategorieValidator.validate(categorieDTO);
        if(!errors.isEmpty()){
            log.error("Category is not empty", categorieDTO);
            throw new InvalidEntityException("La catégorie n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }

        Categorie savedCategorie = categorieRepository.save(CategorieDTO.toEntity(categorieDTO));
        return CategorieDTO.fromEntity(savedCategorie);
    }

    @Override
    public CategorieDTO findById(Integer id) {
        if(id == null){
            log.error("Category ID is null");
            return  null;
        }

        return categorieRepository.findById(id)
                .map(CategorieDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "La catégorie avec l'ID " + id + "n'a pas été retrouvé",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));

        /*Optional<Categorie> categorie = categorieRepository.findById(id);
        CategorieDTO categorieDTO = CategorieDTO.fromEntity(categorie.get());
        return Optional.of(categorieDTO).orElseThrow(() -> new EntityNotFoundException(
                "La catégorie avec l'ID " + id + "n'a pas été retrouvé", ErrorCodes.CATEGORY_NOT_FOUND
            )
        );*/
    }

    @Override
    public CategorieDTO findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Category CODE is null");
            return  null;
        }

        return categorieRepository.findCategoryByCode(code)
                .map(CategorieDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "La catégorie avec le code " + code + "n'a pas été retrouvé", ErrorCodes.CATEGORY_NOT_FOUND
                ));

        /*Optional<Categorie> categorie = categorieRepository.findCategoryByCode(code);
        CategorieDTO categorieDTO = CategorieDTO.fromEntity(categorie.get());

        return Optional.of(categorieDTO).orElseThrow(() -> new EntityNotFoundException(
                "La catégorie avec le code " + code + "n'a pas été retrouvé", ErrorCodes.CATEGORY_NOT_FOUND
        ));*/
    }

    @Override
    public List<CategorieDTO> findAll() {
        return categorieRepository.findAll().stream()
                .map(CategorieDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
           log.error("le catégorie n'existe pas");
           return;
        }

        categorieRepository.deleteById(id);
    }
}
