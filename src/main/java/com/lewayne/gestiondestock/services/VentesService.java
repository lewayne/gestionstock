package com.lewayne.gestiondestock.services;

import com.lewayne.gestiondestock.dto.VentesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VentesService {
    VentesDTO save(VentesDTO ventesDTO);

    VentesDTO findById(Integer id);

    VentesDTO findByCode(String code);

    List<VentesDTO> findAll();

    void delete(Integer id);
}
