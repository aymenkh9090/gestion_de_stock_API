package com.gestiondestock.mapper;

import com.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.gestiondestock.model.LigneCommandeFournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LigneCommandeFournisseurMapper {
    LigneCommandeFournisseurMapper INSTANCE = Mappers.getMapper(LigneCommandeFournisseurMapper.class);
    LigneCommandeFournisseurDto toDto (LigneCommandeFournisseur ligneCommandeFournisseur);
    LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto);
}
