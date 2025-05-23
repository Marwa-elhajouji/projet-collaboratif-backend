package com.projet.projet_collaboratif.controller;

import com.projet.projet_collaboratif.model.Tache;
import com.projet.projet_collaboratif.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @GetMapping
    public List<Tache> getAllTaches() {
        return tacheService.getAllTaches();
    }

    @GetMapping("/{id}")
    public Optional<Tache> getTacheById(@PathVariable Long id) {
        return tacheService.getTacheById(id);
    }

    @PostMapping
    public Tache createTache(@RequestBody Tache tache) {
        return tacheService.saveTache(tache);
    }

    @DeleteMapping("/{id}")
    public void deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
    }

    @PutMapping("/{id}")
    public Tache updateTache(@PathVariable Long id, @RequestBody Tache updatedTache) {
        return tacheService.getTacheById(id)
            .map(tache -> {
                tache.setTitre(updatedTache.getTitre());
                tache.setDescription(updatedTache.getDescription());
                tache.setDateDebut(updatedTache.getDateDebut());
                tache.setDateFin(updatedTache.getDateFin());
                tache.setStatut(updatedTache.getStatut());
                tache.setProjet(updatedTache.getProjet());
                tache.setAssignéA(updatedTache.getAssignéA());
                return tacheService.saveTache(tache);
            })
            .orElseThrow(() -> new RuntimeException("Tâche non trouvée avec l'id : " + id));
    }
}
