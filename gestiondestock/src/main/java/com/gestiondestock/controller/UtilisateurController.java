package com.gestiondestock.controller;

import com.gestiondestock.controller.api.UtilisateurApi;
import com.gestiondestock.dto.UtilisateurDto;
import com.gestiondestock.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurDto> save(UtilisateurDto utilisateurDto) {
        UtilisateurDto savedUtilisateurDto = utilisateurService.save(utilisateurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUtilisateurDto);
    }

    @Override
    public ResponseEntity<UtilisateurDto> findById(Integer id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @Override
    public ResponseEntity<List<UtilisateurDto>> findAll() {
        return ResponseEntity.ok(utilisateurService.findAll());
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UtilisateurDto> findByEmail(String email) {
        return ResponseEntity.ok(utilisateurService.findByEmail(email));
    }
}
