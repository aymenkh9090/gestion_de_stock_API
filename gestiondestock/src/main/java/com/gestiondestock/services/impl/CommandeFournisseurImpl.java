package com.gestiondestock.services.impl;

import com.gestiondestock.dto.*;
import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.CommandeFournisseurMapper;
import com.gestiondestock.model.*;
import com.gestiondestock.repository.*;
import com.gestiondestock.services.CommandeFournisseurService;
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
@Slf4j
@RequiredArgsConstructor
public class CommandeFournisseurImpl implements CommandeFournisseurService {
    private final ObjectValidator<CommandeFournisseurDto> objectValidator;
    private final FournisseurRepository fournisseurRepository;
    private final ArticleRepository articleRepository;
    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final CommandeFournisseurMapper commandeFournisseurMapper;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Override
    @Transactional
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        //1- validation pour la commandeFrournisseur
        if(commandeFournisseurDto == null){
            throw new InvalidEntityException("Commande Fournisseur invalide", ErrorsCode.FOURNISSEUR_NOT_VALID);
        }
        objectValidator.validate(commandeFournisseurDto);
        //2-- Verfication de l'exitence de fournisseur
        Optional<Fournisseur> fournisseur =
                fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if(fournisseur.isEmpty()){
            log.warn("Fournisseur with ID {} not found",commandeFournisseurDto.getFournisseur().getId());
            throw  new EntityNotFoundException("Fournisseur with ID:" + commandeFournisseurDto.getFournisseur().getId()+"not found",ErrorsCode.FOURNISSEUR_NOT_FOUND);
        }
        //3-- Verficiation sur les lignes commandes fournisseurs-->chaque commande doit contenir au moins une ligne
        if(commandeFournisseurDto.getLigneCommandeFournisseurs()==null || commandeFournisseurDto.getLigneCommandeFournisseurs().isEmpty()){
            log.warn("La Commande doit contenir au moins une ligne de commande");
            throw new EntityNotFoundException("Ligne Commande Invalid", ErrorsCode.COMMANDE_FOURNISSEUR_NOT_VALID);
        }
        //4--Verfication des articles dans ligne commande --> ligne doit contenir au moins un article
        List<String> articleErrors = new ArrayList<>();
        commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligCmd->{
            if(ligCmd.getArticle()==null || ligCmd.getArticle().getId()==null){
                articleErrors.add("Impossible d'enregistrer une commande avec une article invalid!!!");
            } else if (!articleRepository.existsById(ligCmd.getArticle().getId())) {
                articleErrors.add("L'article avec l'ID:"+ligCmd.getArticle().getId()+"n'existe pas");
            }
        });
        if(!articleErrors.isEmpty()){
            throw new InvalidEntityException("Certains Article n'existe Pas",ErrorsCode.ARTICLE_NOT_VALID,articleErrors);
        }
        //5--sauvegarde la commande
        commandeFournisseurDto.setDateCommande(Instant.now());
        CommandeFournisseur savedCommandeFournisseur=
                commandeFournisseurRepository.save(commandeFournisseurMapper.toEntity(commandeFournisseurDto));
        //6--sauvegarde ligne commande
        if(commandeFournisseurDto.getLigneCommandeFournisseurs()!=null){
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligCmd->{
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmd);
                ligneCommandeFournisseur.setCommandefournisseur(savedCommandeFournisseur);
                ligneCommandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
                LigneCommandeFournisseur savedLigneCmd = ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if(id==null){
            log.error("id is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(commandeFournisseurMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException(
                        "CommandeClient with id: " + id + " not found",
                        ErrorsCode.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if(code==null){
            log.error("code is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeClientByCode(code)
                .map(commandeFournisseurMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException(
                        "CommandeClient with id: " + code + " not found",
                        ErrorsCode.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll()
                .stream()
                .map(commandeFournisseurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id==null){
            log.error("id de commande is null");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }
    private void effectuerEntree(LigneCommandeFournisseur lig) {
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .article(ArticleDto.fromEntity(lig.getArticle()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvtStk.ENTREE)
                .sourceMvt(SourceMvtStk.COMMANDE_FOURNISSEUR)
                .quantite(lig.getQuantite())
                .idEntreprise(lig.getIdEntreprise())
                .build();
        //mvtStkService.entreeStock(mvtStkDto);
    }

}
