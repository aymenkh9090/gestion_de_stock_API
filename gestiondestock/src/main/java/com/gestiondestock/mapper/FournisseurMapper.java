package com.gestiondestock.mapper;

import com.gestiondestock.dto.FournisseurDto;
import com.gestiondestock.model.Fournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FournisseurMapper {
    FournisseurMapper INSTANCE = Mappers.getMapper(FournisseurMapper.class);
    FournisseurDto toDto(Fournisseur fournisseur);
    Fournisseur toEntity(FournisseurDto fournisseurDto);
}
