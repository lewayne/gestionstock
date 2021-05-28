package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.CommandeClientDTO;
import com.lewayne.gestiondestock.dto.CommandeFournisseurDTO;
import com.lewayne.gestiondestock.dto.LigneCommandeFournisseurDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.*;
import com.lewayne.gestiondestock.repository.*;
import com.lewayne.gestiondestock.services.CommandeFournisseurService;
import com.lewayne.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private ArticleRepository articleRepository;
    private FournisseurRepository fournisseurRepository;

    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, ArticleRepository articleRepository, FournisseurRepository fournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.articleRepository = articleRepository;
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public CommandeFournisseurDTO save(CommandeFournisseurDTO commandeFournisseurDTO) {
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDTO);

        if(!errors.isEmpty()){
            log.error("Comande Fournisseur is not valid");
            throw new InvalidEntityException("la commande Fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDTO.getFournisseur().getId());

        if(!fournisseur.isEmpty()){
            log.warn("Fournisseur with ID" + commandeFournisseurDTO.getFournisseur().getId() + "was not present in the database");
            throw new EntityNotFoundException("Aucun Fournisseur avec l'ID { " + commandeFournisseurDTO.getFournisseur().getId()  + " } ",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if(commandeFournisseurDTO.getLigneCommandeFournisseurs() !=null){ // une ligne de commande existe déjà ?? OUI
            commandeFournisseurDTO.getLigneCommandeFournisseurs().forEach(ligneCmdfss -> { //on parcours toutes ces lignes de commandeFournisseur
                if(ligneCmdfss.getArticle() != null){ // et pour chaque ligne on verifi que l'article existe déjà dans notre base de données
                    Optional<Article> article = articleRepository.findById(ligneCmdfss.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("l'article avec le code" +ligneCmdfss.getArticle().getId()+ "n'existe pas dans la base de données");
                    }
                }
                else{
                    articleErrors.add("Impossible d'enregistrer une commande avec un article null");
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("le ou les articles n'existe pas dans notre base de données", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur savedCmdFss = commandeFournisseurRepository.save(CommandeFournisseurDTO.toEntity(commandeFournisseurDTO));


        if(commandeFournisseurDTO.getLigneCommandeFournisseurs() != null){
            commandeFournisseurDTO.getLigneCommandeFournisseurs().forEach(lignCmdFss ->{
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDTO.toEntity(lignCmdFss);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFss);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDTO.fromEntity(savedCmdFss);
    }

    @Override
    public CommandeFournisseurDTO findById(Integer id) {
        if(id == null){
            log.error("Commande Fournisseur ID is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("pas de commande trouvé avec cet ID  " + id, ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDTO findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Commande Fournisseur CODE is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("pas de commande trouvé avec cet CODE  " + code, ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDTO> findAll() {
        return commandeFournisseurRepository.findAll()
                .stream()
                .map(CommandeFournisseurDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("commande Fournisseur ID is null");
            return;
        }

        commandeFournisseurRepository.deleteById(id);
    }

}
