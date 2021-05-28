package com.lewayne.gestiondestock.services;

import com.lewayne.gestiondestock.dto.EntrepriseDTO;

import java.util.List;

public interface EntrepriseService {
    EntrepriseDTO save(EntrepriseDTO dto);

    EntrepriseDTO findById(Integer id);

    List<EntrepriseDTO> findAll();

    void delete(Integer id);
}
