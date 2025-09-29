package com.gestiondestock.controller.api;
import com.gestiondestock.dto.ClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.gestiondestock.utils.Constantes.APP_ROOT;
public interface ClientApi {
    @PostMapping(APP_ROOT+"/clients/save")
    ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto);
    @GetMapping(APP_ROOT+"/clients/id/{id}")
    ResponseEntity<ClientDto> findById(@PathVariable("id") Integer id);
    @GetMapping(APP_ROOT+"/clients/all")
    ResponseEntity <List<ClientDto>> findAll();
    @DeleteMapping(APP_ROOT+"/clients/delete/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") Integer id);

}
