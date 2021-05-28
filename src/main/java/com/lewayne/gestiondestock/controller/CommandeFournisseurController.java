package com.lewayne.gestiondestock.controller;

import com.lewayne.gestiondestock.controller.api.CommandeFournisseurApi;
import com.lewayne.gestiondestock.dto.CommandeFournisseurDTO;
import com.lewayne.gestiondestock.services.CommandeFournisseurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private CommandeFournisseurService commandeFournisseurService;

    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDTO> save(CommandeFournisseurDTO commandeFournisseurDTO) {
        return ResponseEntity.ok(commandeFournisseurService.save(commandeFournisseurDTO));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDTO> findById(Integer id) {
        return ResponseEntity.ok(commandeFournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDTO> findByCode(String code) {
        return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDTO>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.ok().build();
    }
}
