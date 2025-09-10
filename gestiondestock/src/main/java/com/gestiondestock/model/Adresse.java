package com.gestiondestock.model;


import com.gestiondestock.dto.AdresseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class Adresse  {

    @Column(name = "adresse1")
    private String adresse1;

    @Column(name = "adresse2")
    private String adresse2;

    @Column(name = "ville")
    private String ville;

    @Column(name = "codepostal")
    private String codePostal;

    @Column(name = "pays")
    private String pays;


}
