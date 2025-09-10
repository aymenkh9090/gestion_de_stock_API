package com.gestiondestock.dto;

import com.gestiondestock.model.Article;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {

    private Integer id;
    private String codeArticle;
    private String designation;
    private BigDecimal prixUnitaireHt;
    private BigDecimal tauxTva;
    private BigDecimal prixUnitaireTtc;
    private String photo;
    private Integer idEntreprise;
    private CategoryDto category;

    // Entity --> DTO
    public static ArticleDto fromEntity(Article article) {
        if(article == null) return null;
        return  ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .idEntreprise(article.getIdEntreprise())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }

    //DTO --> Entity
    public static Article toEntity(ArticleDto articleDto) {
        if(articleDto == null) return null;
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setCategory(CategoryDto.ToEntity(articleDto.getCategory()));
        return article;
    }






}
