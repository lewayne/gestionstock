package com.lewayne.gestiondestock.repository;

import com.lewayne.gestiondestock.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
