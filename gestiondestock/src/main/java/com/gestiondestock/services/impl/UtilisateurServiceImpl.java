package com.gestiondestock.services.impl;

import com.gestiondestock.dto.UtilisateurDto;
import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.UtilisateurMapper;
import com.gestiondestock.repository.UtilisateurRepository;
import com.gestiondestock.services.UtilisateurService;
import com.gestiondestock.validators.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final ObjectValidator<UtilisateurDto> objectValidator;

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        if(utilisateurDto == null) {
            throw new InvalidEntityException("Invalid Fournisseur" , ErrorsCode.UTILISATEUR_NOT_VALID);
        }
        objectValidator.validate(utilisateurDto);
        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateurMapper.toEntity(utilisateurDto)));

    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if(id == null) {
            throw  new InvalidEntityException("Invalid id" , ErrorsCode.UTILISATEUR_NOT_VALID);
        }
        return utilisateurRepository.findById(id)
                .map(utilisateurMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur Not Found",ErrorsCode.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {

        return utilisateurRepository.findAll()
                .stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(Integer id) {
        if(id == null) {
            throw new InvalidEntityException("Invalid id" , ErrorsCode.UTILISATEUR_NOT_VALID);
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto findByEmail(String email) {

        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(utilisateurMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException("Utilisateur Not Found",ErrorsCode.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public UtilisateurDto changerMotDePasse(UtilisateurDto utilisateurDto) {
        return null;
        
    }
}
