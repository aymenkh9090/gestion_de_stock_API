package com.gestiondestock.services;

import com.gestiondestock.dto.CategoryDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CategoryService {

    CategoryDto save (CategoryDto category);
    CategoryDto findById(Integer id);
    CategoryDto findByCode(String code);
    List<CategoryDto> findAll();
    void deleteById(Integer id);


}
