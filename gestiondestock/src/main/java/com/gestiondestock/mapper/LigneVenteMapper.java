package com.gestiondestock.mapper;

import com.gestiondestock.dto.LigneVenteDto;
import com.gestiondestock.model.LigneVente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LigneVenteMapper {
    LigneVenteMapper INSTANCE = Mappers.getMapper(LigneVenteMapper.class);
    LigneVenteDto toDto(LigneVente ligneVente);
    LigneVente toEntity(LigneVenteDto LigneVenteDto);
}
