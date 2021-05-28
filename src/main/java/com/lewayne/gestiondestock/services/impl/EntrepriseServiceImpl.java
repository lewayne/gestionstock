package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.EntrepriseDTO;
import com.lewayne.gestiondestock.dto.FournisseurDTO;
import com.lewayne.gestiondestock.dto.RolesDTO;
import com.lewayne.gestiondestock.dto.UtilisateurDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.Entreprise;
import com.lewayne.gestiondestock.repository.EntrepriseRepository;
import com.lewayne.gestiondestock.repository.RolesRepository;
import com.lewayne.gestiondestock.services.EntrepriseService;
import com.lewayne.gestiondestock.services.UtilisateurService;
import com.lewayne.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    private UtilisateurService utilisateurService;
    private RolesRepository rolesRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService,
                                 RolesRepository rolesRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public EntrepriseDTO save(EntrepriseDTO dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseDTO savedEntreprise = EntrepriseDTO.fromEntity(
                entrepriseRepository.save(EntrepriseDTO.toEntity(dto))
        );

        UtilisateurDTO utilisateur = fromEntreprise(savedEntreprise);

        UtilisateurDTO savedUser = utilisateurService.save(utilisateur);

        RolesDTO rolesDto = RolesDTO.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        rolesRepository.save(RolesDTO.toEntity(rolesDto));

        return  savedEntreprise;
    }

    private UtilisateurDTO fromEntreprise(EntrepriseDTO dto) {
        return UtilisateurDTO.builder()
                .adresse(dto.getAdresse())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .moteDePasse(generateRandomPassword())
                .entreprise(dto)
                .dateDeNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "lewayne1";
    }

    @Override
    public EntrepriseDTO findById(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return null;
        }
        return entrepriseRepository.findById(id)
                .map(EntrepriseDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
                );
    }

    @Override
    public List<EntrepriseDTO> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}