package com.lewayne.gestiondestock.controller.api;

import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.dto.CommandeClientDTO;
import com.lewayne.gestiondestock.dto.EntrepriseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lewayne.gestiondestock.utils.Constantes.APP_ROOT;

public interface EntrepriseApi {

    @PostMapping(APP_ROOT + "/entreprise/create")
    ResponseEntity<EntrepriseDTO> save(@RequestBody EntrepriseDTO dto);

    @GetMapping(APP_ROOT + "/entreprise/{idEntreprise}")
    ResponseEntity<EntrepriseDTO> findById(Integer id);

    @GetMapping(value = APP_ROOT + "/entreprise/all")
    ResponseEntity<List<EntrepriseDTO>> findAll();

    @DeleteMapping(APP_ROOT + "/entreprise/delete/{idEntreprise}")
    ResponseEntity delete(@PathVariable("idEntreprise") Integer id);
}
