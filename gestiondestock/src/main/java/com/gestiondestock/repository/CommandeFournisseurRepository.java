package com.gestiondestock.repository;

import com.gestiondestock.model.CommandeClient;
import com.gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    Optional<CommandeFournisseur> findCommandeClientByCode(String code);
   // List<CommandeFournisseur> findAllByFournisseur(Integer id);
}
