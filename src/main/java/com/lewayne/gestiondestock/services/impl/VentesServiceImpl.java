package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.ArticleDTO;
import com.lewayne.gestiondestock.dto.LigneVenteDTO;
import com.lewayne.gestiondestock.dto.VentesDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.model.Article;
import com.lewayne.gestiondestock.model.LigneVente;
import com.lewayne.gestiondestock.model.Ventes;
import com.lewayne.gestiondestock.repository.ArticleRepository;
import com.lewayne.gestiondestock.repository.LigneVenteRepository;
import com.lewayne.gestiondestock.repository.VentesRepository;
import com.lewayne.gestiondestock.services.VentesService;
import com.lewayne.gestiondestock.validator.VentesValidator;
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
public class VentesServiceImpl implements VentesService {
    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDTO save(VentesDTO ventesDTO) {
        List<String> errors = VentesValidator.validate(ventesDTO);
        if(!errors.isEmpty()){
            log.error("Ventes n'est pas valide", errors);
            throw  new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        ventesDTO.getLigneVentes().forEach(ligneVenteDTO -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDTO.getArticle().getId());
            if(article.isEmpty()){
                articleErrors.add("Aucun article avec l'ID " + ligneVenteDTO.getArticle().getId() + "n'a été trouvé dans la BB");
            }
        });

        if(!articleErrors.isEmpty()){
            log.error("un ou plusieurs articles pas trouvés", articleErrors);
            throw new InvalidEntityException("un ou plusieurs articles pas trouvés", ErrorCodes.VENTE_NOT_VALID, articleErrors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDTO.toEntity(ventesDTO));

        ventesDTO.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDTO.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
            //updateMvtStk(ligneVente);
        });

        return VentesDTO.fromEntity(savedVentes);
    }

    @Override
    public VentesDTO findById(Integer id) {
        if(id == null){
            log.warn("Vente ID is null");
            return  null;
        }

        return ventesRepository.findById(id)
                .map(VentesDTO::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException(
                                "aucune vente avec l' ID = "  + id + " n'a été trouvé dans la base de données", ErrorCodes.VENTE_NOT_VALID
                        )
                );
    }

    @Override
    public VentesDTO findByCode(String code) {
        if(!StringUtils.hasLength((code))){
            log.error("Vente CODE is null");
            return  null;
        }

        return ventesRepository.findVentesByCode(code)
                .map(VentesDTO::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException(
                                "aucune vente avec le code = "  + code + " n'a été trouvé dans la base de données", ErrorCodes.VENTE_NOT_VALID
                        )
                );
    }

    @Override
    public List<VentesDTO> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDTO::fromEntity)  //mapping, on map d'un objet à un autre
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Vente ID is null");
            return;
        }
        ventesRepository.deleteById(id);
        //articleRepository.deleteAll();
    }
}
