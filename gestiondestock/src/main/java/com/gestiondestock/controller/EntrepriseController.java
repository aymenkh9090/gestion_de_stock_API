package com.gestiondestock.controller;

import com.gestiondestock.controller.api.EntrepriseApi;
import com.gestiondestock.dto.EntrepriseDto;
import com.gestiondestock.services.EntrepriseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EntrepriseController implements EntrepriseApi {

    private final EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public ResponseEntity<EntrepriseDto> save(EntrepriseDto entrepriseDto) {
        EntrepriseDto entrepriseDtoSaved = entrepriseService.save(entrepriseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrepriseDtoSaved);
    }

    @Override
    public ResponseEntity<List<EntrepriseDto>> findAll() {
        return ResponseEntity.ok(entrepriseService.findAll());
    }

    @Override
    public ResponseEntity<List<EntrepriseDto>> findAllById(Integer id) {
        return ResponseEntity.ok(entrepriseService.findAllById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        entrepriseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
