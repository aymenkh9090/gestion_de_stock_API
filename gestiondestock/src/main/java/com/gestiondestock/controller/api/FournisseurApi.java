package com.gestiondestock.controller.api;
import com.gestiondestock.dto.FournisseurDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gestiondestock.utils.Constantes.APP_ROOT;


public interface FournisseurApi {


    @PostMapping(APP_ROOT+"/fournisseurs/create")
    ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto);
    @GetMapping(APP_ROOT+"/fournisseurs/all")
    ResponseEntity<List<FournisseurDto>> findAll();
    @GetMapping(APP_ROOT+"/fournisseurs/id/{id}")
    ResponseEntity<List<FournisseurDto>> findAllById(@PathVariable("id") Integer id);
    @DeleteMapping(APP_ROOT+"/fournisseurs/delete/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") Integer id);





}
