package com.gestiondestock.services.impl;

import com.gestiondestock.dto.EntrepriseDto;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.EntrepriseMapper;
import com.gestiondestock.repository.ClientRepository;
import com.gestiondestock.repository.EntrepriseRepository;
import com.gestiondestock.services.EntrepriseService;
import com.gestiondestock.validators.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;
    private final ObjectValidator<EntrepriseDto> validator;
    private final EntrepriseMapper entrepriseMapper;
    private final ObjectValidator objectValidator;

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        if(entrepriseDto==null){
            throw new InvalidEntityException("Entreprise Not Valid!!" , ErrorsCode.ENTREPRISE_NOT_VALID);
        }
        validator.validate(entrepriseDto);
        return

                entrepriseMapper.toDto(
                  entrepriseRepository.save(entrepriseMapper.toEntity(entrepriseDto))
                );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll()
                .stream()
                .map(entrepriseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntrepriseDto> findAllById(Integer id) {
        if(id==null){
            throw new InvalidEntityException( "Entreprise Invalid",ErrorsCode.ENTREPRISE_NOT_VALID);
        }
        return entrepriseRepository.findAllById(id)
                .stream().map(entrepriseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id==null){
            throw new InvalidEntityException( "Entreprise Invalid",ErrorsCode.ENTREPRISE_NOT_VALID);
        }
        entrepriseRepository.deleteById(id);
    }
}
