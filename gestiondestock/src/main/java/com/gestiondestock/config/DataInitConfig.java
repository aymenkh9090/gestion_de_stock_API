package com.gestiondestock.config;

import com.gestiondestock.dto.ArticleDto;
import com.gestiondestock.dto.CategoryDto;
import com.gestiondestock.model.Article;
import com.gestiondestock.model.Category;
import com.gestiondestock.repository.ArticleRepository;
import com.gestiondestock.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class DataInitConfig {


    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

       // @Bean
        CommandLineRunner initData(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
            return args -> {
                CategoryDto categoryDto = CategoryDto.builder()
                        .code("er")
                        .designation("aaaadff")
                        .idEntreprise(4)
                        .build();
                Category categorySaved = CategoryDto.ToEntity(categoryDto);

                categoryRepository.save(categorySaved);
                ArticleDto dto = ArticleDto.builder()
                        .codeArticle("aze")
                        .designation("aaaa")
                        .prixUnitaireTtc(BigDecimal.valueOf(74))
                        .tauxTva(BigDecimal.valueOf(1))
                        .prixUnitaireHt(BigDecimal.valueOf(74))
                        .idEntreprise(1)
                        .category(categoryDto)
                        .build();
                Article savedArticle = ArticleDto.toEntity(dto);
                articleRepository.save(savedArticle);
            };
        }









}
