package com.gestiondestock.services;

import com.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);
    UtilisateurDto findById(Integer id);
    List<UtilisateurDto> findAll();
    void deleteById(Integer id);
    UtilisateurDto findByEmail(String email);
    UtilisateurDto changerMotDePasse(UtilisateurDto utilisateurDto);




}
