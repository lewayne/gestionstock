package com.lewayne.gestiondestock.services;

import com.lewayne.gestiondestock.dto.CommandeClientDTO;
import com.lewayne.gestiondestock.dto.LigneCommandeClientDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDTO save(CommandeClientDTO commandeClientDTO);

    CommandeClientDTO findById(Integer id);

    CommandeClientDTO findByCode(String code);

    List<CommandeClientDTO> findAll();

    void delete(Integer id);

    //CommandeClientDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    /*CommandeClientDTO updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeClientDTO updateClient(Integer idCommande, Integer idClient);

    CommandeClientDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);

    // Delete article ==> delete LigneCommandeClient
    CommandeClientDTO deleteArticle(Integer idCommande, Integer idLigneCommande);

    List<LigneCommandeClientDTO> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);*/
}
