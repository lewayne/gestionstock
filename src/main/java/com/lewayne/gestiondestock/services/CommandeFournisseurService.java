package com.lewayne.gestiondestock.services;

import com.lewayne.gestiondestock.dto.CommandeFournisseurDTO;
import com.lewayne.gestiondestock.dto.LigneCommandeFournisseurDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {
    CommandeFournisseurDTO save(CommandeFournisseurDTO commandeFournisseurDTO);

    CommandeFournisseurDTO findById(Integer id);

    CommandeFournisseurDTO findByCode(String code);

    List<CommandeFournisseurDTO> findAll();

    void delete(Integer id);

    //CommandeFournisseurDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    /*CommandeFournisseurDTO updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeFournisseurDTO  updateFournisseur(Integer idCommande, Integer idFournisseur);

    CommandeFournisseurDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

    // Delete article ==> delete LigneCommandeFournisseur
    CommandeFournisseurDTO deleteArticle(Integer idCommande, Integer idLigneCommande);

    List<LigneCommandeFournisseurDTO> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);*/
}
