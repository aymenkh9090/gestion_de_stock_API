package com.gestiondestock.services;

import com.gestiondestock.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle(String codeArticle);
    List<ArticleDto> findAll();
    List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);
    void deleteById(Integer id);






}
