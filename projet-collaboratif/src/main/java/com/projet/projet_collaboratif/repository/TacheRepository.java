package com.projet.projet_collaboratif.repository;

import com.projet.projet_collaboratif.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache, Long> {
}
