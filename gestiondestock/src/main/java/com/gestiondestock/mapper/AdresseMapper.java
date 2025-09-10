package com.gestiondestock.mapper;

import com.gestiondestock.dto.AdresseDto;
import com.gestiondestock.model.Adresse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

    AdresseMapper INSTANCE = Mappers.getMapper(AdresseMapper.class);
    //Entity --> Dto
    AdresseDto toDto(Adresse adresse);
    // Dto-->Entity
    Adresse toEntity(AdresseDto adresseDto);


}
