package com.lewayne.gestiondestock.controller;

import com.lewayne.gestiondestock.controller.api.VentesApi;
import com.lewayne.gestiondestock.dto.VentesDTO;
import com.lewayne.gestiondestock.services.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {
    private VentesService ventesService;

    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public ResponseEntity<VentesDTO>  save(VentesDTO ventesDTO) {
        return ResponseEntity.ok(ventesService.save(ventesDTO));
    }

    @Override
    public ResponseEntity<VentesDTO>  findById(Integer id) {
        return ResponseEntity.ok(this.ventesService.findById(id));
    }

    @Override
    public ResponseEntity<VentesDTO> findByCode(String code) {
        return ResponseEntity.ok(ventesService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<VentesDTO>> findAll() {
        return ResponseEntity.ok(ventesService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        this.ventesService.delete(id);
        return ResponseEntity.ok().build();
    }
}
