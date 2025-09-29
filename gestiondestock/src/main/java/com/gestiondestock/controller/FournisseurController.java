package com.gestiondestock.controller;

import com.gestiondestock.controller.api.FournisseurApi;
import com.gestiondestock.dto.FournisseurDto;
import com.gestiondestock.services.FournisseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ResponseEntity<FournisseurDto> save(FournisseurDto fournisseurDto) {
        FournisseurDto savedDto = fournisseurService.save(fournisseurDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @Override
    public ResponseEntity<List<FournisseurDto>> findAll() {
        return ResponseEntity.ok(fournisseurService.findAll());
    }

    @Override
    public ResponseEntity<List<FournisseurDto>> findAllById(Integer id) {
        return ResponseEntity.ok(fournisseurService.findAllById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        fournisseurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
