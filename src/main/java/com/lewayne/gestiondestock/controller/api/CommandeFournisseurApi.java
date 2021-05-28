package com.lewayne.gestiondestock.controller.api;

import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.dto.CategorieDTO;
import com.lewayne.gestiondestock.dto.CommandeClientDTO;
import com.lewayne.gestiondestock.dto.CommandeFournisseurDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.lewayne.gestiondestock.utils.Constantes.*;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    @PostMapping(CREATE_COMMANDE_FOURNISSEUR_ENDPOINT)
    ResponseEntity<CommandeFournisseurDTO> save(CommandeFournisseurDTO commandeFournisseurDTO);

    @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT)
    ResponseEntity<CommandeFournisseurDTO> findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT)
    ResponseEntity<CommandeFournisseurDTO> findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT)
    ResponseEntity<List<CommandeFournisseurDTO>> findAll();

    @DeleteMapping(DELETE_COMMANDE_FOURNISSEUR_ENDPOINT)
    ResponseEntity delete(@PathVariable("idCommandeFournisseur") Integer id);
}
