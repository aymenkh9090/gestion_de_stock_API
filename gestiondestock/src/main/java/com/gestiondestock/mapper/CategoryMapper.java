package com.gestiondestock.mapper;
import com.gestiondestock.dto.CategoryDto;
import com.gestiondestock.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    AdresseMapper INSTANCE = Mappers.getMapper(AdresseMapper.class);

    // Entity -> DTO
    CategoryDto toDto(Category category);

    // DTO -> Entity
    Category toEntity(CategoryDto categoryDto);

}
