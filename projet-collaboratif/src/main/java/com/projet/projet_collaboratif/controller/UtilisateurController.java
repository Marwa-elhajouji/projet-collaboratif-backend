package com.projet.projet_collaboratif.controller;

import com.projet.projet_collaboratif.model.Utilisateur;
import com.projet.projet_collaboratif.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public Optional<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
    @PutMapping("/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur updatedUser) {
    return utilisateurService.getUtilisateurById(id)
        .map(utilisateur -> {
            utilisateur.setNom(updatedUser.getNom());
            utilisateur.setEmail(updatedUser.getEmail());
            utilisateur.setMotDePasse(updatedUser.getMotDePasse());
            return utilisateurService.saveUtilisateur(utilisateur);
        })
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
}

}
