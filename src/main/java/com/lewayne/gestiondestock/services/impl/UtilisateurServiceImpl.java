package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.FournisseurDTO;
import com.lewayne.gestiondestock.dto.UtilisateurDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.Fournisseur;
import com.lewayne.gestiondestock.model.Utilisateur;
import com.lewayne.gestiondestock.repository.UtilisateurRepository;
import com.lewayne.gestiondestock.services.UtilisateurService;
import com.lewayne.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDTO save(UtilisateurDTO utilisateurDTO) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDTO);
        if(errors.isEmpty()){
            log.error("User is not valid");
            throw new InvalidEntityException("User n'est pas valid", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }

        Utilisateur utilisateur = utilisateurRepository.save(UtilisateurDTO.toEntity(utilisateurDTO));
        return UtilisateurDTO.fromEntity(utilisateur);
    }

    @Override
    public UtilisateurDTO findById(Integer id) {
        if(id == null){
            log.error("Id us null");
            return null;
        }

        return utilisateurRepository.findById(id)
                .map(UtilisateurDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "L'utilisateur dont ID est " + id  + "n'existe pas", ErrorCodes.UTILISATEUR_NOT_VALID
                ));

        /*Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        UtilisateurDTO utilisateurDTO = UtilisateurDTO.fromEntity(utilisateur.get());
        return Optional.of(utilisateurDTO).orElseThrow(() -> new EntityNotFoundException(
                        "L'utilisateur dont ID est " + id  + "n'existe pas", ErrorCodes.UTILISATEUR_NOT_VALID
                )
        );*/
    }

    @Override
    public List<UtilisateurDTO> findAll() {
        return this.utilisateurRepository.findAll().stream()
                .map(UtilisateurDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("IS is null");
            return;
        }

        utilisateurRepository.deleteById(id);
    }

   /* @Override
    public UtilisateurDTO findByEmail(String email) {
        if(!StringUtils.hasLength(email)){
            log.error("Email us null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findUtilisateurByEmail(email);
        UtilisateurDTO utilisateurDTO = UtilisateurDTO.fromEntity(utilisateur.get());
        return Optional.of(utilisateurDTO).orElseThrow(() -> new EntityNotFoundException(
                        "L'utilisateur dont l'EMAIL est " + email  + "n'existe pas", ErrorCodes.UTILISATEUR_NOT_FOUND
                )
        );
    }*/

    @Override
    public UtilisateurDTO findByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email + " n'a ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
                );
    }
}
