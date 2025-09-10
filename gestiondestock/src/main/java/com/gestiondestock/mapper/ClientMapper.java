package com.gestiondestock.mapper;

import com.gestiondestock.dto.ClientDto;
import com.gestiondestock.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
