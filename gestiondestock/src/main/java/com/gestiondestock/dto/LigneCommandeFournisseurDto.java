package com.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestiondestock.model.Article;
import com.gestiondestock.model.CommandeFournisseur;
import com.gestiondestock.model.LigneCommandeFournisseur;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto {
    private Integer id;
    private ArticleDto article;
    @JsonIgnore
    private CommandeFournisseurDto commandeFournisseur;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (ligneCommandeFournisseur == null) {
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto) {
        if (dto == null) {
            return null;
        }

        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(dto.getId());
        ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(dto.getArticle()));
        ligneCommandeFournisseur.setPrixUnitaire(dto.getPrixUnitaire());
        ligneCommandeFournisseur.setQuantite(dto.getQuantite());
        ligneCommandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        return ligneCommandeFournisseur;
    }

}
