package com.gestiondestock.controller;

import com.gestiondestock.controller.api.ArticleApi;
import com.gestiondestock.dto.ArticleDto;
import com.gestiondestock.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity <ArticleDto> save(ArticleDto article) {
        ArticleDto savedArticle = articleService.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @Override
    public ResponseEntity <ArticleDto>findById(Integer id) {
        return ResponseEntity.ok( articleService.findById(id));
    }

    @Override
    public ResponseEntity <ArticleDto> findByCodeArticle(String codeArticle) {
        return ResponseEntity.ok(articleService.findByCodeArticle(codeArticle));
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAllArticleByIdCategory(Integer idCategory) {
        return ResponseEntity.ok(articleService.findAllArticleByIdCategory(idCategory));
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        articleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
