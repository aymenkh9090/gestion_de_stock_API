package com.gestiondestock.mapper;

import com.gestiondestock.dto.CommandeFournisseurDto;
import com.gestiondestock.model.CommandeFournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommandeFournisseurMapper {
        CommandeFournisseurMapper INSTANCE = Mappers.getMapper(CommandeFournisseurMapper.class);
        CommandeFournisseurDto toDto(CommandeFournisseur commande);
        CommandeFournisseur toEntity(CommandeFournisseurDto commandeDto);

}
