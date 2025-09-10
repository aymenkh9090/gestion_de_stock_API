package com.gestiondestock.mapper;

import com.gestiondestock.dto.VentesDto;
import com.gestiondestock.model.Ventes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VentesMapper {
    VentesMapper INSTANCE = Mappers.getMapper(VentesMapper.class);
    VentesDto toDto(Ventes ventes);
    Ventes toEntity(VentesDto ventesDto);
}
