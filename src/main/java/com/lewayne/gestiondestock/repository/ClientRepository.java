package com.lewayne.gestiondestock.repository;

import com.lewayne.gestiondestock.model.Article;
import com.lewayne.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findClientByNom(String name);
    Optional<Client> findClientById(Integer id);
}
