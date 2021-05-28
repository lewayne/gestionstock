package com.lewayne.gestiondestock.services;

import com.lewayne.gestiondestock.dto.UtilisateurDTO;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDTO save(UtilisateurDTO dto);

    UtilisateurDTO findById(Integer id);

    List<UtilisateurDTO> findAll();

    void delete(Integer id);

    UtilisateurDTO findByEmail(String email);

    //UtilisateurDTO changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);
}
