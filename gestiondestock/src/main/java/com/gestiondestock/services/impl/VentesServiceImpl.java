package com.gestiondestock.services.impl;

import com.gestiondestock.dto.ArticleDto;
import com.gestiondestock.dto.MvtStkDto;
import com.gestiondestock.dto.VentesDto;
import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.exceptions.ErrorsCode;
import com.gestiondestock.exceptions.InvalidEntityException;
import com.gestiondestock.mapper.LigneVenteMapper;
import com.gestiondestock.mapper.VentesMapper;
import com.gestiondestock.model.LigneVente;
import com.gestiondestock.model.SourceMvtStk;
import com.gestiondestock.model.TypeMvtStk;
import com.gestiondestock.model.Ventes;
import com.gestiondestock.repository.ArticleRepository;
import com.gestiondestock.repository.LigneVenteRepository;
import com.gestiondestock.repository.VentesRepository;
import com.gestiondestock.services.ArticleService;
import com.gestiondestock.services.VentesService;
import com.gestiondestock.validators.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VentesServiceImpl implements VentesService {

    private final ObjectValidator<VentesDto> objectValidator;
    private final ArticleRepository articleRepository;
    private final VentesRepository ventesRepository;
    private final VentesMapper ventesMapper;
    private final LigneVenteMapper ligneVenteMapper;
    private final LigneVenteRepository ligneVenteRepository;

    @Override
    @Transactional
    public VentesDto save(VentesDto ventesDto) {
       //1--validation de la ventes
        if(ventesDto==null){
            throw new InvalidEntityException("ventes n'est pas valide", ErrorsCode.VENTE_NOT_VALID);
        }
        objectValidator.validate(ventesDto);
        //2--verification sur la ligne vente
        if(ventesDto.getLigneVentes()==null || ventesDto.getLigneVentes().isEmpty()){
            log.warn("La Commande doit contenir au moins une ligne de commande");
            throw new EntityNotFoundException("Ligne Commande Invalid", ErrorsCode.VENTE_NOT_VALID);
        }
        //3- verification sur les articles dans la ligne de vente
        List<String> articlesErrors = new ArrayList<>();
        ventesDto.getLigneVentes().forEach(vente -> {
           if(vente.getArticle()==null || vente.getArticle().getId()==null){
               articlesErrors.add("Impossible d'enregistrer une vente avec une Article null");
           } else if (!articleRepository.existsById(vente.getArticle().getId())) {
               articlesErrors.add("Impossible d'enregistrer vente avec article n'existe pas");
           }
        });
        if(!articlesErrors.isEmpty()){
            throw new InvalidEntityException("Certains Article Invalid",ErrorsCode.ARTICLE_NOT_VALID,articlesErrors);
        }
        //4--enregistrer la vente
        Ventes savedVentes = ventesRepository.save(ventesMapper.toEntity(ventesDto));
        //5--enregistrement de les lignes de ventes
        ventesDto.getLigneVentes().forEach(vntdto -> {
            LigneVente ligneVente = ligneVenteMapper.toEntity(vntdto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
            updateMvtStk(ligneVente);

        });
        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if(id==null){
            log.error("Ventes ID is null");
        }
        return ventesRepository.findById(id)
                .map(ventesMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException("Aucun vente n'ete trouve dans la bdd",ErrorsCode.VENTE_NOT_VALID));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(ventesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if(id==null){
            log.error("Ventes ID is null");
            return;
        }
        ventesRepository.deleteById(id);
    }
    private void updateMvtStk(LigneVente lig) {
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .article(ArticleDto.fromEntity(lig.getArticle()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvtStk.SORTIE)
                .sourceMvt(SourceMvtStk.VENTE)
                .quantite(lig.getQuantite())
                .idEntreprise(lig.getIdEntreprise())
                .build();
       // mvtStkService.sortieStock(mvtStkDto);
    }
}
