package com.gestiondestock.services.impl;

import com.gestiondestock.dto.ArticleDto;
import com.gestiondestock.dto.CommandeClientDto;
import com.gestiondestock.dto.LigneCommandeClientDto;
import com.gestiondestock.dto.MvtStkDto;
import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.CommandeClientMapper;
import com.gestiondestock.mapper.LigneCommandeClientMapper;
import com.gestiondestock.model.*;
import com.gestiondestock.repository.ArticleRepository;
import com.gestiondestock.repository.ClientRepository;
import com.gestiondestock.repository.CommandeClientRepository;
import com.gestiondestock.repository.LigneCommandeClientRepository;
import com.gestiondestock.services.CommandeClientService;
import com.gestiondestock.validators.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private final ObjectValidator<CommandeClientDto> objectValidator;
    private final CommandeClientRepository commandeClientRepository;
    private final CommandeClientMapper commandeClientMapper;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final LigneCommandeClientMapper ligneCommandeClientMapper;
    LigneCommandeClientRepository ligneCommandeClientRepository;

    @Override
    @Transactional
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        // 1--Validation de commandcliendto
        if (commandeClientDto == null) {
            throw new InvalidEntityException("Commande Client Invalid", ErrorsCode.COMMANDE_CLIENT_NOT_VALID);
        }
        objectValidator.validate(commandeClientDto);

        //2-- Verifcation de l'exitence du client
        Optional<Client> client =
                clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client with id{} not found", commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("Client with ID: " + commandeClientDto.getClient().getId() + " not found");
        }

        //3--- Verification de la ligneCommande -->  chaque commande doit contenir au moins une ligne commande
        if (commandeClientDto.getLigneCommandeClient() == null || commandeClientDto.getLigneCommandeClient().isEmpty()) {
            log.warn("La Commande doit contenir au moins une ligne de commande");
            throw new EntityNotFoundException("Ligne Commande Invalid", ErrorsCode.COMMANDE_CLIENT_NOT_VALID);
        }

        //4-- Verification des article dont ligne commande --> ligne doit conntenir au moins une article
        List<String> articleErrors = new ArrayList<>();
        commandeClientDto.getLigneCommandeClient().forEach(ligneComm -> {
            if (ligneComm.getArticle() == null || ligneComm.getArticle().getId() == null) {
                articleErrors.add("Impossible d'enregitrer une Commande avec une Article null");
            } else if (!articleRepository.existsById(ligneComm.getArticle().getId())) {
                articleErrors.add("L'Article avec l'ID " + ligneComm.getArticle().getId() + " n'existe pas ");
            }
        });
        if (!articleErrors.isEmpty()) {
            throw new InvalidEntityException("Certains Article n'existe pas", ErrorsCode.ARTICLE_NOT_FOUND, articleErrors);
        }

        //5-- sauvegarde la commande
        commandeClientDto.setDateCommande(Instant.now());
        CommandeClient savedCommandeClient =
                commandeClientRepository.save(commandeClientMapper.toEntity(commandeClientDto));
        //6-- sauvegarde ligne comnde + Mise a jour stock
        if (commandeClientDto.getLigneCommandeClient() != null) {
            commandeClientDto.getLigneCommandeClient().forEach(ligneComm -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneComm);
                ligneCommandeClient.setCommandeclient(savedCommandeClient);
                ligneCommandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
                LigneCommandeClient savedLigneCmd = ligneCommandeClientRepository.save(ligneCommandeClient);
                effectuerSortie(savedLigneCmd);
            });

        }

        return CommandeClientDto.fromEntity(savedCommandeClient);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if(id==null){
            log.error("id is null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(commandeClientMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException(
                        "CommandeClient with id: " + id + " not found",
                        ErrorsCode.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(code==null){
            log.error("code is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(commandeClientMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException(
                        "CommandeClient with id: " + code + " not found",
                        ErrorsCode.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll()
                .stream()
                .map(commandeClientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id==null){
            log.error("id de commande is null");
            return;
        }
        commandeClientRepository.deleteById(id);
    }


    private void effectuerSortie(LigneCommandeClient lig) {
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .article(ArticleDto.fromEntity(lig.getArticle()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvtStk.SORTIE)
                .sourceMvt(SourceMvtStk.COMMANDE_CLIENT)
                .quantite(lig.getQuantite())
                .idEntreprise(lig.getIdEntreprise())
                .build();
        //mvtStkService.sortieStock(mvtStkDto);
    }


    /*@Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        if (commandeClientDto == null) {
            throw new InvalidEntityException("Commande Client Invalid", ErrorsCode.COMMANDE_CLIENT_NOT_VALID);
        }
        objectValidator.validate(commandeClientDto);

        // validation de client
        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("client with ID {} not found", commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("Client with ID" + commandeClientDto.getClient().getId() + " not found");
        }

        // verification de ligne commande
        if (commandeClientDto.getLigneCommandeClient() == null || commandeClientDto.getLigneCommandeClient().isEmpty()) {
            log.warn("la commande doit contnir au moins une ligne");
            throw new InvalidEntityException("Ligne CommandeClient Invalid", ErrorsCode.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
        }
        // verification d'article dans ligne commande
        commandeClientDto.getLigneCommandeClient().forEach(ligneCommande -> {
            if (ligneCommande.getArticle() == null || ligneCommande.getArticle().getId() == null) {
                log.warn("Article non disponible");
                throw new InvalidEntityException("Chaque Ligne doit contenir une article", ErrorsCode.ARTICLE_NOT_VALID);
            }
            // verification de l'article Id dans la base de donne
            boolean exist = articleRepository.existsById(ligneCommande.getArticle().getId());
            if (!exist) {
                log.warn("Article with ID {} not found", ligneCommande.getArticle().getId());
                throw new InvalidEntityException("Article with ID" + ligneCommande.getArticle().getId() + " not found");
            }
        });

        // sauvegarde commande
        commandeClientDto.setDateCommande(Instant.now());
        CommandeClient commandeClient = commandeClientMapper.toEntity(commandeClientDto);
        CommandeClient savedCommande = commandeClientRepository.save(commandeClient);
        // sauvegarde ligne commande
        commandeClientDto.getLigneCommandeClient().forEach(ligneCommandeDto -> {
            LigneCommandeClient ligne = ligneCommandeClientMapper.toEntity(ligneCommandeDto);
            ligne.setCommandeclient(savedCommande);
            ligneCommandeClientRepository.save(ligne);
        });

        return commandeClientMapper.toDto(commandeClientRepository.save(commandeClient));
    }*/
}
