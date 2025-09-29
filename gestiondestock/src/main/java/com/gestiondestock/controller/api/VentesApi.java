package com.gestiondestock.controller.api;
import com.gestiondestock.dto.VentesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gestiondestock.utils.Constantes.APP_ROOT;

public interface VentesApi {


    @PostMapping(APP_ROOT+"/ventes/create")
    ResponseEntity <VentesDto> save(@RequestBody VentesDto ventesDto);
    @GetMapping(APP_ROOT+"/ventes/id/{id}")
    ResponseEntity <VentesDto> findById(@PathVariable("id") Integer id);
    @GetMapping(APP_ROOT+"/all")
    ResponseEntity <List<VentesDto>> findAll();
    @DeleteMapping(APP_ROOT+"/ventes/delete/{id}")
    ResponseEntity <Void> deleteById(@PathVariable("id") Integer id);
    @GetMapping(APP_ROOT+"/ventes/code/{code}")
    ResponseEntity <VentesDto> findByCode(@PathVariable("code") String code);







}
