package com.lewayne.gestiondestock.controller.api;

import com.lewayne.gestiondestock.dto.ClientDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.lewayne.gestiondestock.utils.Constantes.APP_ROOT;

public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDTO save(ClientDTO clientDTO);

    @GetMapping(value = APP_ROOT + "/clients/{nameClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDTO findByName(@PathVariable("nameClient") String name);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDTO findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDTO> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idArticle}")
    void delete(@PathVariable("idArticle") Integer id);
}
