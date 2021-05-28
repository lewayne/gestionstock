package com.lewayne.gestiondestock.controller;

import com.lewayne.gestiondestock.controller.api.ClientApi;
import com.lewayne.gestiondestock.dto.ClientDTO;
import com.lewayne.gestiondestock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        clientService = clientService;
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        return clientService.save(clientDTO);
    }

    @Override
    public ClientDTO findByName(String name) {
        return clientService.findByNom(name);
    }

    @Override
    public ClientDTO findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);
    }
}
