package com.lewayne.gestiondestock.controller.api;

import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.dto.CategorieDTO;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lewayne.gestiondestock.utils.Constantes.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategorieApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une catégorie", notes = "Cette methode permet d'enregistrer ou modifier une categorie", response = CategorieDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet catégorie cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet catégorie n'est pas valide")
    })
    CategorieDTO save(@RequestBody CategorieDTO categorieDTO );

    @GetMapping(value = APP_ROOT + "/categories/{idCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par ID", notes = "Cette methode permet de chercher une categorie par son ID", response = ArticleDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategorieDTO findById(@PathVariable("idCategorie")  Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une v par CODE", notes = "Cette methode permet de chercher une categorie par son CODE", response = CategorieDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec le CODE fournis")
    })
    CategorieDTO findByCode(@ApiParam("Accepted value [CAT-1, CAT-2, CAT-3, CAT-4]") @PathVariable("codeCategorie") String code);
    //CategorieDTO findByCode(@ApiParam("Accepted value [CAT-1, CAT-2, CAT-3, CAT-4]") @PathVariable("codeCategorie") String code);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des categories", notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existent " + "dans la BDD", responseContainer = "List<CategorieDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide")
    })
    List<CategorieDTO> findAll();

    @DeleteMapping(value = APP_ROOT + "/categorie/delete/{idCategorie}")
    @ApiOperation(value = "Supprimer une categorie", notes = "Cette methode permet de supprimer une categorie par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete supprime")
    })
    void delete(@PathVariable("idCategorie") Integer id);
}
