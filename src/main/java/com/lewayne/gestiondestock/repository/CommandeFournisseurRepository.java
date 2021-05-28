package com.lewayne.gestiondestock.repository;

import com.lewayne.gestiondestock.model.CommandeClient;
import com.lewayne.gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
    List<CommandeClient> findAllByFournisseurId(Integer id);
}
