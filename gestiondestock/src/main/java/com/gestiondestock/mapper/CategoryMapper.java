package com.gestiondestock.mapper;
import com.gestiondestock.dto.CategoryDto;
import com.gestiondestock.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    AdresseMapper INSTANCE = Mappers.getMapper(AdresseMapper.class);

    // Entity -> DTO
    @Mapping(target = "articles", ignore = true)
    CategoryDto toDto(Category category);

    // DTO -> Entity
    Category toEntity(CategoryDto categoryDto);

}
