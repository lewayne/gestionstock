package com.lewayne.gestiondestock.services;

import com.lewayne.gestiondestock.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    ClientDTO save(ClientDTO clientDTO);

    ClientDTO findByNom(String name);

    ClientDTO findById(Integer id);

    List<ClientDTO> findAll();

    void delete(Integer id);
}
