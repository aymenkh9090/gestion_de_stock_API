package com.gestiondestock.services.impl;

import com.gestiondestock.dto.ClientDto;
import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.ClientMapper;
import com.gestiondestock.repository.ClientRepository;
import com.gestiondestock.services.ClientService;
import com.gestiondestock.validators.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ObjectValidator<ClientDto> objectValidator;



    @Override
    public ClientDto save(ClientDto clientDto) {
        if(clientDto == null) {
            throw new InvalidEntityException("Client Not Found" , ErrorsCode.CLIENT_NOT_VALID);
        }
        objectValidator.validate(clientDto);
        return clientMapper.toDto(
                clientRepository.save(clientMapper.toEntity(clientDto))
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if(id == null) {
            throw new InvalidEntityException("Client Not Found" , ErrorsCode.CLIENT_NOT_VALID);
        }
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Client Not Found" , ErrorsCode.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id == null) {
            throw new InvalidEntityException("Client Not Found" , ErrorsCode.CLIENT_NOT_VALID);
        }
        clientRepository.deleteById(id);
    }
}
