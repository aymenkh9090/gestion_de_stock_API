package com.gestiondestock.mapper;

import com.gestiondestock.dto.EntrepriseDto;
import com.gestiondestock.model.Entreprise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntrepriseMapper {
    EntrepriseMapper INSTANCE = Mappers.getMapper(EntrepriseMapper.class);
    EntrepriseDto toDto(Entreprise entreprise);
    Entreprise toEntity(EntrepriseDto entrepriseDto);
}
