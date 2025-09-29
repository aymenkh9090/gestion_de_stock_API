package com.gestiondestock.services;

import com.gestiondestock.dto.VentesDto;

import java.util.List;

public interface VentesService {


    VentesDto save(VentesDto ventesDto);
    VentesDto findById(Integer id);
    List<VentesDto> findAll();
    void deleteById(Integer id);




}
