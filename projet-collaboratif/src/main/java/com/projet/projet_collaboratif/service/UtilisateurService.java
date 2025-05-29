package com.projet.projet_collaboratif.service;

import com.projet.projet_collaboratif.model.Utilisateur;
import com.projet.projet_collaboratif.repository.RoleRepository;
import com.projet.projet_collaboratif.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.projet_collaboratif.model.Role;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;


    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
    if (utilisateur.getRole() != null && utilisateur.getRole().getId() != null) {
        Role roleComplet = roleRepository.findById(utilisateur.getRole().getId())
                .orElseThrow(
                        () -> new RuntimeException("Rôle introuvable avec l'id : " + utilisateur.getRole().getId()));
        utilisateur.setRole(roleComplet);
    } else {
        throw new RuntimeException("Aucun rôle fourni");
    }
    return utilisateurRepository.save(utilisateur);
}

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
    return utilisateurRepository.findById(id)
            .map(u -> {
                u.setNom(utilisateur.getNom());
                u.setEmail(utilisateur.getEmail());
                u.setMotDePasse(utilisateur.getMotDePasse());
                return utilisateurRepository.save(u);
            })
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
}
public void setUtilisateurRepository(UtilisateurRepository repo) {
    this.utilisateurRepository = repo;
}

public void setRoleRepository(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
}


}
