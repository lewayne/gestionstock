package com.lewayne.gestiondestock.services;

import com.lewayne.gestiondestock.dto.CategorieDTO;

import java.util.List;


public interface CategorieService {
    CategorieDTO save(CategorieDTO categorieDTO );

    CategorieDTO findById(Integer id);

    CategorieDTO findByCode(String code);

    List<CategorieDTO> findAll();

    void delete(Integer id);

}
