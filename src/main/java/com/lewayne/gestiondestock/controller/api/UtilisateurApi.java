package com.lewayne.gestiondestock.controller.api;

import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.dto.UtilisateurDTO;
import com.lewayne.gestiondestock.model.Utilisateur;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lewayne.gestiondestock.utils.Constantes.APP_ROOT;

@Api(APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @PostMapping(APP_ROOT + "/utilisateurs/create")
    ResponseEntity<UtilisateurDTO> save(@RequestBody UtilisateurDTO dto);

    @GetMapping(APP_ROOT + "/utilisateurs/{idUtilisateur}")
    ResponseEntity<UtilisateurDTO> findById(Integer id);

    @GetMapping(APP_ROOT + "/utilisateurs/{emailUtilisateur}")
    ResponseEntity<UtilisateurDTO> findByEmail(@PathVariable("emailUtilisateur") String email);

    @GetMapping(APP_ROOT + "/utilisateurs/all")
    ResponseEntity<List<UtilisateurDTO>> findAll();

    @DeleteMapping(APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
    ResponseEntity delete(@PathVariable("idUtilisateur") Integer id);
}
