package com.gestiondestock.services;

import com.gestiondestock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto fournisseurDto);
    List<FournisseurDto> findAll();
    List<FournisseurDto> findAllById(Integer id);
    void deleteById(Integer id);


}
