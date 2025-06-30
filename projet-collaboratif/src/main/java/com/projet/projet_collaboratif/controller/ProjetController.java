package com.projet.projet_collaboratif.controller;

import com.projet.projet_collaboratif.model.Projet;
import com.projet.projet_collaboratif.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @GetMapping
    public ResponseEntity<List<Projet>> getAllProjets() {
        return ResponseEntity.ok(projetService.getAllProjets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjetById(@PathVariable Long id) {
        return projetService.getProjetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
        Projet created = projetService.saveProjet(projet);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable Long id, @RequestBody Projet updatedProjet) {
        return projetService.getProjetById(id)
                .map(projet -> {
                    projet.setNom(updatedProjet.getNom());
                    projet.setDescription(updatedProjet.getDescription());
                    Projet saved = projetService.saveProjet(projet);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.ok().build();
    }
}
