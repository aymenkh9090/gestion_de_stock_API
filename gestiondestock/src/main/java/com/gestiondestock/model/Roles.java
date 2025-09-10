package com.gestiondestock.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "roles")
public class Roles extends AbstractEntity {


    @Column(name = "rolname")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;

}
