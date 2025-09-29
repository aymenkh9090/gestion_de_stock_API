package com.gestiondestock.controller;

import com.gestiondestock.controller.api.VentesApi;
import com.gestiondestock.dto.VentesDto;
import com.gestiondestock.services.VentesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {

    private final VentesService venteService;

    public VentesController(VentesService venteService) {
        this.venteService = venteService;
    }

    @Override
    public ResponseEntity<VentesDto> save(VentesDto ventesDto) {
        VentesDto savedVentes = venteService.save(ventesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVentes);
    }

    @Override
    public ResponseEntity<VentesDto> findById(Integer id) {
        return ResponseEntity.ok(venteService.findById(id));
    }

    @Override
    public ResponseEntity<List<VentesDto>> findAll() {
        return ResponseEntity.ok(venteService.findAll());
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        venteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<VentesDto> findByCode(String code) {
        return ResponseEntity.ok(venteService.findByCode(code));
    }
}
