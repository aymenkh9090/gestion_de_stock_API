package com.gestiondestock.services.impl;

import com.gestiondestock.dto.FournisseurDto;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.FournisseurMapper;
import com.gestiondestock.repository.FournisseurRepository;
import com.gestiondestock.services.FournisseurService;
import com.gestiondestock.validators.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final FournisseurMapper fournisseurMapper;
    private final ObjectValidator<FournisseurDto> fournisseurValidator;

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        if(fournisseurDto == null) {
            throw new InvalidEntityException("Invalid Fournisseur" , ErrorsCode.FOURNISSEUR_NOT_VALID);
        }
        fournisseurValidator.validate(fournisseurDto);
        return fournisseurMapper.toDto(fournisseurRepository.save(fournisseurMapper.toEntity(fournisseurDto)));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll()
                .stream()
                .map(fournisseurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FournisseurDto> findAllById(Integer id) {
        return fournisseurRepository.findAllById(id)
                .stream()
                .map(fournisseurMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(Integer id) {
        if(id == null) {
            throw new InvalidEntityException("Invalid id" , ErrorsCode.FOURNISSEUR_NOT_VALID);
        }
        fournisseurRepository.deleteById(id);
    }
}
