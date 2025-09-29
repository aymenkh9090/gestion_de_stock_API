package com.gestiondestock.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestiondestock.dto.ArticleDto;
import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.ArticleMapper;
import com.gestiondestock.model.Article;
import com.gestiondestock.repository.ArticleRepository;
import com.gestiondestock.services.ArticleService;
import com.gestiondestock.validators.ObjectValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final ObjectValidator<ArticleDto> validator;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper, ObjectValidator objectValidator) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.validator = objectValidator;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        if(articleDto==null){
            throw new InvalidEntityException("Invalid Article", ErrorsCode.ARTICLE_NOT_VALID);
        }
        validator.validate(articleDto);
        return articleMapper.toDto(articleRepository.save(articleMapper.toEntity(articleDto)));

    }

    @Override
    public ArticleDto findById(Integer id) {
        if(id==null){
            throw new InvalidEntityException("Invalid Article", ErrorsCode.ARTICLE_NOT_VALID);

        }
        return articleRepository.findById(id)
                .map(articleMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException("Article with This Id : "+id+" Not Found", ErrorsCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if(codeArticle==null){
            throw new InvalidEntityException("Invalid Article", ErrorsCode.ARTICLE_NOT_VALID);
        }
        return articleRepository.findArticleByCodeArticle(codeArticle)
                .map(articleMapper::toDto)
                .orElseThrow(()->new InvalidEntityException("No Article found with code : " + codeArticle, ErrorsCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {

        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
        if(idCategory==null){
            throw new InvalidEntityException("Article Not found with this id of Category :" +idCategory, ErrorsCode.ARTICLE_NOT_VALID);
        }
        return articleRepository.findAllByCategoryId(idCategory)
                .stream()
                .map(article->articleMapper.toDto(article))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id==null){
            throw new InvalidEntityException("Invalid Article", ErrorsCode.ARTICLE_NOT_VALID);
        }
        articleRepository.deleteById(id);
    }
}
