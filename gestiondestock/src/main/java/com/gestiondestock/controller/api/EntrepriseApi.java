package com.gestiondestock.controller.api;

import com.gestiondestock.dto.EntrepriseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.gestiondestock.utils.Constantes.APP_ROOT;
public interface EntrepriseApi {

    @PostMapping(APP_ROOT+"/entreprises/create")
    ResponseEntity<EntrepriseDto> save(@RequestBody EntrepriseDto entrepriseDto);
    @GetMapping(APP_ROOT+"/entreprises/all")
    ResponseEntity<List<EntrepriseDto>> findAll();
    @GetMapping(APP_ROOT+"/entreprises/id/{idEntreprise}")
    ResponseEntity <List<EntrepriseDto>> findAllById(@PathVariable("idEntreprise") Integer id);
    @DeleteMapping(APP_ROOT+"/delete/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);
}
