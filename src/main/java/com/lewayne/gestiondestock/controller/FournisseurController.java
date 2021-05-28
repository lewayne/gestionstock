package com.lewayne.gestiondestock.controller;

import com.lewayne.gestiondestock.controller.api.FournisseurApi;
import com.lewayne.gestiondestock.dto.FournisseurDTO;
import com.lewayne.gestiondestock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ResponseEntity<FournisseurDTO> save(FournisseurDTO fournisseurDTO) {
        return ResponseEntity.ok(fournisseurService.save(fournisseurDTO));
    }

    @Override
    public ResponseEntity<FournisseurDTO> findById(Integer id) {
        return ResponseEntity.ok(fournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<List<FournisseurDTO>> findAll() {
        return ResponseEntity.ok(fournisseurService.findAll());
    }

    @Override
    public ResponseEntity<FournisseurDTO> delete(Integer id) {
        this.fournisseurService.delete(id);
        return ResponseEntity.ok().build();
    }
}
