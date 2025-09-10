package com.gestiondestock.dto;

import com.gestiondestock.model.Adresse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdresseDto {


    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostal;
    private String pays;

    public static AdresseDto fromEntity(Adresse adresse) {
        if(adresse == null){
            return null;
        }
        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .ville(adresse.getVille())
                .codePostal(adresse.getCodePostal())
                .pays(adresse.getPays())
                .build();
    }

    public static  Adresse toEntity(AdresseDto adressDto){
        if(adressDto == null){
            return null;
        }
        Adresse adresse = new Adresse();
        adresse.setAdresse1(adressDto.getAdresse1());
        adresse.setAdresse2(adressDto.getAdresse2());
        adresse.setVille(adressDto.getVille());
        adresse.setCodePostal(adressDto.getCodePostal());
        adresse.setPays(adressDto.getPays());
        return adresse;
    }

}
