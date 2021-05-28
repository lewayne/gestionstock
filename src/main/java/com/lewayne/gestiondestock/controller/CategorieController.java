package com.lewayne.gestiondestock.controller;

import com.lewayne.gestiondestock.controller.api.CategorieApi;
import com.lewayne.gestiondestock.dto.CategorieDTO;
import com.lewayne.gestiondestock.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController implements CategorieApi {

    private CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Override
    public CategorieDTO save(CategorieDTO categorieDTO) {
        return this.categorieService.save(categorieDTO);
    }

    @Override
    public CategorieDTO findById(Integer id) {
        return this.categorieService.findById(id);
    }

    @Override
    public CategorieDTO findByCode(String code) {
        return this.categorieService.findByCode(code);
    }

    @Override
    public List<CategorieDTO> findAll() {
        return this.findAll();
    }

    @Override
    public void delete(Integer id) {
        this.delete(id);
    }
}
