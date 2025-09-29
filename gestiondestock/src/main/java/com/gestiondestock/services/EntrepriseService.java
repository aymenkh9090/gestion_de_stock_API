package com.gestiondestock.services;

import com.gestiondestock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {


    EntrepriseDto save(EntrepriseDto entrepriseDto);
    List<EntrepriseDto> findAll();
    List<EntrepriseDto> findAllById(Integer id);
    void deleteById(Integer id);




}
