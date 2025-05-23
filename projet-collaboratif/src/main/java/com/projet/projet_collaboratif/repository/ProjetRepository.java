package com.projet.projet_collaboratif.repository;

import com.projet.projet_collaboratif.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
}
