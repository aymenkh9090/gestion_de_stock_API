package com.gestiondestock.mapper;

import com.gestiondestock.dto.RolesDto;
import com.gestiondestock.model.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    RolesMapper INSTANCE = Mappers.getMapper(RolesMapper.class);
    RolesDto toDto(Roles roles);
    Roles toEntity(RolesDto rolesDto);
}
