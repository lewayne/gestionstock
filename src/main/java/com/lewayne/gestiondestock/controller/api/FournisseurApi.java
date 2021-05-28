package com.lewayne.gestiondestock.controller.api;

import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.dto.FournisseurDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lewayne.gestiondestock.utils.Constantes.APP_ROOT;

@Api(APP_ROOT + "/fournisseur")
public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseur/create")
    ResponseEntity<FournisseurDTO> save(@RequestBody  FournisseurDTO fournisseurDTO);

    @GetMapping(value = APP_ROOT + "/fournisseur/{idFournisseur}")
    ResponseEntity<FournisseurDTO> findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseur/all")
    ResponseEntity<List<FournisseurDTO>> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseur/delete/{idFournisseur}")
    ResponseEntity delete(@PathVariable("idFournisseur") Integer id);
}
