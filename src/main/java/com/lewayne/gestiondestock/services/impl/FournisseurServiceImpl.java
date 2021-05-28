package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.CategorieDTO;
import com.lewayne.gestiondestock.dto.EntrepriseDTO;
import com.lewayne.gestiondestock.dto.FournisseurDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.Entreprise;
import com.lewayne.gestiondestock.model.Fournisseur;
import com.lewayne.gestiondestock.repository.FournisseurRepository;
import com.lewayne.gestiondestock.services.FournisseurService;
import com.lewayne.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService{

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDTO save(FournisseurDTO fournisseurDTO) {
        List<String> errors = FournisseurValidator.validate(fournisseurDTO);
        if(errors.isEmpty()){
            log.error("Fournisseur is not valid");
            throw new InvalidEntityException("le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }

        Fournisseur fournisseur = fournisseurRepository.save(FournisseurDTO.toEntity(fournisseurDTO));
        return FournisseurDTO.fromEntity(fournisseur);
    }

    @Override
    public FournisseurDTO findById(Integer id) {
        if(id == null){
            log.error("Id us null");
            return null;
        }

        return fournisseurRepository.findById(id)
                .map(FournisseurDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Le fourniseur dont ID est " + id  + "n'existe pas", ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));

        /* Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        FournisseurDTO fournisseurDTO = FournisseurDTO.fromEntity(fournisseur.get());
        return Optional.of(fournisseurDTO).orElseThrow(() -> new EntityNotFoundException(
                        "Le fourniseur dont ID est " + id  + "n'existe pas", ErrorCodes.FOURNISSEUR_NOT_FOUND
                )
        );*/
    }

    @Override
    public List<FournisseurDTO> findAll() {
        return this.fournisseurRepository.findAll().stream()
                .map(FournisseurDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("ID is null");
            return;
        }

        fournisseurRepository.deleteById(id);
    }
}
