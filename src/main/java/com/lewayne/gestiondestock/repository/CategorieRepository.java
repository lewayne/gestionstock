package com.lewayne.gestiondestock.repository;

import com.lewayne.gestiondestock.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Optional<Categorie> findCategoryByCode(String code);
}
