package com.projet.projet_collaboratif.controller;

import com.projet.projet_collaboratif.model.Projet;
import com.projet.projet_collaboratif.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @GetMapping
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }

    @GetMapping("/{id}")
    public Optional<Projet> getProjetById(@PathVariable Long id) {
        return projetService.getProjetById(id);
    }

    @PostMapping
    public Projet createProjet(@RequestBody Projet projet) {
        return projetService.saveProjet(projet);
    }

    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
    }

    @PutMapping("/{id}")
    public Projet updateProjet(@PathVariable Long id, @RequestBody Projet updatedProjet) {
        return projetService.getProjetById(id)
            .map(projet -> {
                projet.setNom(updatedProjet.getNom());
                projet.setDescription(updatedProjet.getDescription());
                projet.setDateDebut(updatedProjet.getDateDebut());
                projet.setDateFin(updatedProjet.getDateFin());
                projet.setResponsable(updatedProjet.getResponsable());
                return projetService.saveProjet(projet);
            })
            .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'id : " + id));
    }
}
