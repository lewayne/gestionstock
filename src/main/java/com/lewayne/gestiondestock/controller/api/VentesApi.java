package com.lewayne.gestiondestock.controller.api;

import com.lewayne.gestiondestock.dto.VentesDTO;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lewayne.gestiondestock.utils.Constantes.APP_ROOT;

@Api(APP_ROOT + "/ventes")
public interface VentesApi {

    @PostMapping(APP_ROOT + "/ventes/create")
    ResponseEntity<VentesDTO> save(@RequestBody VentesDTO ventesDTO);

    @GetMapping(APP_ROOT + "/ventes/{idVente}")
    ResponseEntity<VentesDTO>  findById(Integer id);

    @GetMapping(APP_ROOT + "/ventes/{codeVente}")
    ResponseEntity<VentesDTO>  findByCode(String code);

    @GetMapping(APP_ROOT + "/ventes/all")
    ResponseEntity<List<VentesDTO>> findAll();

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{idVente}")
    ResponseEntity delete(@PathVariable("idVente") Integer id);
}
