package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.CommandeClientDTO;
import com.lewayne.gestiondestock.dto.LigneCommandeClientDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.Article;
import com.lewayne.gestiondestock.model.Client;
import com.lewayne.gestiondestock.model.CommandeClient;
import com.lewayne.gestiondestock.model.LigneCommandeClient;
import com.lewayne.gestiondestock.repository.ArticleRepository;
import com.lewayne.gestiondestock.repository.ClientRepository;
import com.lewayne.gestiondestock.repository.CommandeClientRepository;
import com.lewayne.gestiondestock.repository.LigneCommandeClientRepository;
import com.lewayne.gestiondestock.services.CommandeClientService;
import com.lewayne.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ArticleRepository articleRepository;
    private ClientRepository clientRepository;


    @Autowired
    public CommandeClientImpl(CommandeClientRepository commandeClientRepository, LigneCommandeClientRepository ligneCommandeClientRepository, ArticleRepository articleRepository, ClientRepository clientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public CommandeClientDTO save(CommandeClientDTO commandeClientDTO) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDTO);

        if(!errors.isEmpty()){
            log.error("Comande client is not valid");
            throw new InvalidEntityException("lacommande Client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        //validation m??tier
        // on verifie que le client qui passe cette commande existe dans la base de donn??es
        Optional<Client> client = clientRepository.findClientById(commandeClientDTO.getClient().getId());
        if(!client.isEmpty()){
            log.warn("Client with ID" + commandeClientDTO.getClient().getId() + "was not present in the database");
            //on peut cr??e une exception m??tier,
            throw new EntityNotFoundException("Aucun client avec l'ID { " + commandeClientDTO.getClient().getId()  + " } ", ErrorCodes.CLIENT_NOT_FOUND);
        }

        //XX
        // pour enregistrer une commande client, il faut que l'article existe d??j?? dans notre base de donn??es
        List<String> articleErrors = new ArrayList<>();

        //A quel moment on cr??e les lignes commandes clients ????

        //on verifie que ligneCommandeClient existe d??j?? dans la base de donn??es, pour cela on v??rifi juste que l'article existe d??j?? dans la BD
        if(commandeClientDTO.getLigneCommandeClients() !=null){ // une ligne de commande existe d??j?? ?? OUI
            commandeClientDTO.getLigneCommandeClients().forEach(ligneCmd -> { //on parcours toutes ces lignes de commandeClient
                if(ligneCmd.getArticle() != null){ // et pour chaque ligne on verifi que l'article existe d??j?? dans notre base de donn??es
                    Optional<Article> article = articleRepository.findById(ligneCmd.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("l'article avec le code" +ligneCmd.getArticle().getId()+ "n'existe pas dans la base de donn??es");
                    }
                }
                else{
                    articleErrors.add("Impossible d'enregistrer une commande avec un article null");
                }
            });
        }

        // si au moins un article n'existe pas dans la base de donn??es ou ??rreurs alors on doit renvoy?? une exception
        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("le ou les articles n'existe pas dans notre base de donn??es", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        // si la lignecommande existe et si tous les articles concern??s par la commande existe dans la base de donn??e alors on va enregistrer la commande du client
        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDTO.toEntity(commandeClientDTO));

        // on doit assign?? cette commande enregistr??e ?? une ligne commande client
        // quand j'enregistre une commande pour la premi??re fois en principe il n'a aucune ligne de commande
        // il y juste les articles.
        // comment on fais pour cr??e une nouvelle ligne de commande ???
        if(commandeClientDTO.getLigneCommandeClients() != null){
            commandeClientDTO.getLigneCommandeClients().forEach(lignCmdClt ->{
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDTO.toEntity(lignCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDTO.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDTO findById(Integer id) {
        if(id == null){
            log.error("Commande client ID is null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("pas de commande trouv?? avec cet ID  " + id, ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDTO findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Commande client CODE is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("pas de commande trouv?? avec cet CODE  " + code, ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDTO> findAll() {
        return commandeClientRepository.findAll()
                .stream()
                .map(CommandeClientDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("commande client ID is null");
            return;
        }

        commandeClientRepository.deleteById(id);
    }
}
