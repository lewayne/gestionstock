package com.lewayne.gestiondestock.repository;

import com.lewayne.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseurRepository, Integer> {
    Optional<CommandeFournisseurRepository> findCommandeFournisseurByCode(String code);

    List<CommandeClient> findAllByFournisseurId(Integer id);
}
