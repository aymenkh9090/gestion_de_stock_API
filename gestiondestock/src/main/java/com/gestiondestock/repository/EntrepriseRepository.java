package com.gestiondestock.repository;

import com.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    List<Entreprise> findAllById(Integer id);
}
