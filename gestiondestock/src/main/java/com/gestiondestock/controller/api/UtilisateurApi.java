package com.gestiondestock.controller.api;

import com.gestiondestock.dto.UtilisateurDto;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.gestiondestock.utils.Constantes.APP_ROOT;
import java.util.List;

public interface UtilisateurApi {

    @PostMapping(APP_ROOT+"/utilisateurs/create")
    ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(APP_ROOT+"/utilisateurs/id/{id}")
    ResponseEntity<UtilisateurDto> findById(@PathVariable("id") Integer id);

    @GetMapping(APP_ROOT+"/utilisateurs/all")
    ResponseEntity <List<UtilisateurDto>> findAll();

    @DeleteMapping(APP_ROOT+"/utilisateurs/delete/{id}")
    ResponseEntity <Void> deleteById(@PathVariable("id") Integer id);

    @GetMapping(APP_ROOT+"/utilisateurs/email/{email}")
    ResponseEntity<UtilisateurDto> findByEmail(@PathVariable("email") String email);

    //ResponseEntity<UtilisateurDto> changerMotDePasse(UtilisateurDto utilisateurDto);












}
