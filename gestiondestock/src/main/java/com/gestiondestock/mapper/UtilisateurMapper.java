package com.gestiondestock.mapper;

import com.gestiondestock.dto.UtilisateurDto;
import com.gestiondestock.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);
    UtilisateurDto toDto(Utilisateur utilisateur);
    Utilisateur toEntity(UtilisateurDto utilisateurDto);
}
