package com.gestiondestock.mapper;

import com.gestiondestock.dto.MvtStkDto;
import com.gestiondestock.model.MvtStk;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MvtStkMapper {
    MvtStkMapper INSTANCE = Mappers.getMapper(MvtStkMapper.class);
    MvtStkDto toDto(MvtStk mvtStk);
    MvtStk toEntity(MvtStkDto MvtStkDto);
}
