package com.gestiondestock.dto;

import com.gestiondestock.model.CommandeFournisseur;
import com.gestiondestock.model.EtatCommande;
import com.gestiondestock.model.Fournisseur;
import com.gestiondestock.model.LigneCommandeFournisseur;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;
    private String code;
    private Instant dateCommande;
    private EtatCommande etatCommande;
    private Integer idEntreprise;
    private FournisseurDto fournisseur;
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null) {
            return null;
        }
        return  CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .etatCommande(commandeFournisseur.getEtatCommande())
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(dto.getId());
        commandeFournisseur.setCode(dto.getCode());
        commandeFournisseur.setDateCommande(dto.getDateCommande());
        commandeFournisseur.setEtatCommande(dto.getEtatCommande());
        commandeFournisseur.setId(dto.getIdEntreprise());
        commandeFournisseur.setFournisseur(FournisseurDto.toEntity(dto.getFournisseur()));
        return commandeFournisseur;
    }

}
