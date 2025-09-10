package com.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.model.Article;
import com.gestiondestock.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDto> articles;

    public static CategoryDto fromEntity(Category category) {
        if(category == null) return null;
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .idEntreprise(category.getIdEntreprise())
                .build();
    }


    public static Category ToEntity(CategoryDto categoryDto)  {
        if(categoryDto == null) {

            return null;

        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setIdEntreprise(categoryDto.getIdEntreprise());
        return category;

    }
}
