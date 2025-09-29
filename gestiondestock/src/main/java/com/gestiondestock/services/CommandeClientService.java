package com.gestiondestock.services;

import com.gestiondestock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto  findByCode(String code);
    List<CommandeClientDto> findAll();
    void deleteById(Integer id);







}
