package com.gestiondestock.mapper;

import com.gestiondestock.dto.LigneCommandeClientDto;
import com.gestiondestock.model.LigneCommandeClient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LigneCommandeClientMapper {
    LigneCommandeClientMapper INSTANCE = Mappers.getMapper(LigneCommandeClientMapper.class);
    LigneCommandeClientDto toDto(LigneCommandeClient ligneCommandeClient);
    LigneCommandeClient toEntity(LigneCommandeClientDto dto);
}
