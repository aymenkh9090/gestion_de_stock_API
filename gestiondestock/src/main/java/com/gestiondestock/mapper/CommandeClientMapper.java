package com.gestiondestock.mapper;

import com.gestiondestock.dto.CommandeClientDto;
import com.gestiondestock.model.CommandeClient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommandeClientMapper {
    CommandeClientMapper INSTANCE = Mappers.getMapper(CommandeClientMapper.class);
    CommandeClientDto toDto(CommandeClient commandeClient);
    CommandeClient toEntity(CommandeClientDto commandeClientDto);
}
