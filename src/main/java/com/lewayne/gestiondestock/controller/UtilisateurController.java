package com.lewayne.gestiondestock.controller;

import com.lewayne.gestiondestock.controller.api.UtilisateurApi;
import com.lewayne.gestiondestock.dto.UtilisateurDTO;
import com.lewayne.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {
    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurDTO> save(UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.ok(utilisateurService.save(utilisateurDTO));
    }

    @Override
    public ResponseEntity<UtilisateurDTO> findById(Integer id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @Override
    public ResponseEntity<UtilisateurDTO> findByEmail(String email) {
        return ResponseEntity.ok(utilisateurService.findByEmail(email));
    }

    @Override
    public ResponseEntity<List<UtilisateurDTO>> findAll() {
        return ResponseEntity.ok(utilisateurService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        this.utilisateurService.delete(id);
        return ResponseEntity.ok().build();
    }
}
