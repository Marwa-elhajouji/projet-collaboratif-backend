package com.projet.projet_collaboratif.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(unique = true)
    private String email;

    private String motDePasse;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
