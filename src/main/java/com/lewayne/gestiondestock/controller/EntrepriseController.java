package com.lewayne.gestiondestock.controller;

import com.lewayne.gestiondestock.controller.api.EntrepriseApi;
import com.lewayne.gestiondestock.dto.EntrepriseDTO;
import com.lewayne.gestiondestock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {
    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public ResponseEntity<EntrepriseDTO> save(EntrepriseDTO entrepriseDTO) {
        return ResponseEntity.ok(entrepriseService.save(entrepriseDTO));
    }

    @Override
    public ResponseEntity<EntrepriseDTO> findById(Integer id) {
        return ResponseEntity.ok(entrepriseService.findById(id));
    }

    @Override
    public ResponseEntity<List<EntrepriseDTO>> findAll() {
        return ResponseEntity.ok(entrepriseService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        entrepriseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
