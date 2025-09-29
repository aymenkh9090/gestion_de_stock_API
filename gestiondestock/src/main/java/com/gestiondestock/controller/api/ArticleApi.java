package com.gestiondestock.controller.api;
import com.gestiondestock.dto.ArticleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gestiondestock.utils.Constantes.APP_ROOT;



public interface ArticleApi {

    @PostMapping(APP_ROOT+"/articles/create")
    ResponseEntity<ArticleDto> save (@RequestBody ArticleDto article);

    @GetMapping(APP_ROOT+"/articles/id/{idArticle}")
    ResponseEntity<ArticleDto> findById(@PathVariable("idArticle") Integer id);

    @GetMapping(APP_ROOT+"/articles/code/{codeArticle}")
    ResponseEntity<ArticleDto> findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(APP_ROOT+"/articles/all")
      ResponseEntity<List<ArticleDto>> findAll();

    @GetMapping(APP_ROOT+"/articles/filter/category/{idCategory}")
     ResponseEntity<List<ArticleDto>> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory);

    @DeleteMapping(APP_ROOT+"/articles/delete/{idArticle}")
     ResponseEntity<Void> deleteById(@PathVariable("idArticle") Integer id);

}
