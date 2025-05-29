package com.projet.projet_collaboratif.service;

import com.projet.projet_collaboratif.model.Role;
import com.projet.projet_collaboratif.model.Utilisateur;
import com.projet.projet_collaboratif.repository.RoleRepository;
import com.projet.projet_collaboratif.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UtilisateurServiceTest {
    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        utilisateurRepository = mock(UtilisateurRepository.class);
        roleRepository = mock(RoleRepository.class);
        utilisateurService = new UtilisateurService();
        utilisateurService.setUtilisateurRepository(utilisateurRepository);
        utilisateurService.setRoleRepository(roleRepository);
    }

    @Test
    void testUpdateUtilisateur_success() {
        Utilisateur existing = new Utilisateur();
        existing.setId(1L);
        existing.setNom("Old");

        Utilisateur update = new Utilisateur();
        update.setNom("New");
        update.setEmail("new@mail.com");
        update.setMotDePasse("pass");

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(utilisateurRepository.save(any())).thenReturn(update);

        Utilisateur result = utilisateurService.updateUtilisateur(1L, update);
        assertEquals("New", result.getNom());
    }

    @Test
    void testUpdateUtilisateur_notFound() {
        when(utilisateurRepository.findById(42L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> utilisateurService.updateUtilisateur(42L, new Utilisateur()));
    }

    @Test
    void testDeleteUtilisateur() {
        doNothing().when(utilisateurRepository).deleteById(1L);
        utilisateurService.deleteUtilisateur(1L);
        verify(utilisateurRepository).deleteById(1L);
    }

    @Test
    void testFindByEmail() {
        Utilisateur u = new Utilisateur();
        u.setEmail("test@mail.com");
        when(utilisateurRepository.findByEmail("test@mail.com")).thenReturn(u);

        Utilisateur result = utilisateurService.findByEmail("test@mail.com");
        assertEquals("test@mail.com", result.getEmail());
    }
    @Test
    void shouldAssignRoleIfNull() {
        Utilisateur u = new Utilisateur();
        u.setNom("Role Auto");

        Role userRole = new Role();
        userRole.setNom("USER");

        when(roleRepository.findAll()).thenReturn(List.of(userRole));
        when(utilisateurRepository.save(any())).thenReturn(u);

    Utilisateur saved = utilisateurService.saveUtilisateur(u);

    assertNotNull(saved);
    assertEquals("Role Auto", saved.getNom());
    verify(utilisateurRepository).save(u);
}

@Test
void shouldThrowIfNoUserRoleFound() {
    Utilisateur u = new Utilisateur();

    when(roleRepository.findAll()).thenReturn(List.of());

    assertThrows(RuntimeException.class, () -> utilisateurService.saveUtilisateur(u));
}

}