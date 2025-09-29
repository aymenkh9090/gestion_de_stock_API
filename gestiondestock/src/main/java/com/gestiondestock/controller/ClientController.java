package com.gestiondestock.controller;

import com.gestiondestock.controller.api.ClientApi;
import com.gestiondestock.dto.ClientDto;
import com.gestiondestock.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientDto> save(ClientDto clientDto) {
        ClientDto clientDtoSaved = clientService.save(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDtoSaved);
    }
    @Override
    public ResponseEntity<ClientDto> findById(Integer id) {
        return ResponseEntity.ok(clientService.findById(id));
    }
    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }
    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
