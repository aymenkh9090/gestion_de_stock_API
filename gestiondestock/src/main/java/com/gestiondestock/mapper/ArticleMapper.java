package com.gestiondestock.mapper;

import com.gestiondestock.dto.ArticleDto;
import com.gestiondestock.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    //Entity-->Dto
    ArticleDto toDto(Article article);
    //Dto->Entity
    Article toEntity(ArticleDto articleDto);
}
