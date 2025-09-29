package com.gestiondestock.controller.api;
import com.gestiondestock.dto.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gestiondestock.utils.Constantes.APP_ROOT;




public interface CategoryApi {

    @PostMapping(APP_ROOT+"/categories/create")
    ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto);

    @GetMapping(APP_ROOT+"/categories/id/{idCategory}")
    ResponseEntity<CategoryDto> findById(@PathVariable("idCategory") Integer idCategory);

    @GetMapping(APP_ROOT+"/categories/code/{codeCategory}")
    ResponseEntity<CategoryDto> findByCode(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(APP_ROOT+"/categories/all")
    ResponseEntity<List<CategoryDto>> findAll();

    @DeleteMapping(APP_ROOT+"/categories/delete{idCategory}")
    ResponseEntity<Void> deleteById(@PathVariable("idCategory") Integer idCategory);









}
