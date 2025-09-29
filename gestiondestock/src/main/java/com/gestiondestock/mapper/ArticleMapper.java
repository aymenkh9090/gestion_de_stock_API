package com.gestiondestock.mapper;

import com.gestiondestock.dto.ArticleDto;
import com.gestiondestock.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    //Entity-->Dto
    @Mapping(target = "category.articles", ignore = true)
    ArticleDto toDto(Article article);
    //Dto->Entity
    @Mapping(target = "category.articles", ignore = true)
    Article toEntity(ArticleDto articleDto);
}
