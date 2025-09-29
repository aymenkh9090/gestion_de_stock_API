package com.gestiondestock.services;

import com.gestiondestock.dto.CommandeClientDto;
import com.gestiondestock.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {
    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);
    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto  findByCode(String code);
    List<CommandeFournisseurDto> findAll();
    void deleteById(Integer id);
}
