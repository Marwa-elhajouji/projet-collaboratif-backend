package com.projet.projet_collaboratif.service;

import com.projet.projet_collaboratif.model.Utilisateur;
import com.projet.projet_collaboratif.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurServiceTest {

    private UtilisateurRepository utilisateurRepository;
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        utilisateurRepository = mock(UtilisateurRepository.class);
        utilisateurService = new UtilisateurService();
        utilisateurService.setUtilisateurRepository(utilisateurRepository);
    }

    @Test
    void testGetAllUtilisateurs() {
        Utilisateur u1 = new Utilisateur();
        u1.setNom("Alice");

        Utilisateur u2 = new Utilisateur();
        u2.setNom("Bob");

        when(utilisateurRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Utilisateur> result = utilisateurService.getAllUtilisateurs();

        assertEquals(2, result.size());
        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    void testGetUtilisateurById() {
        Utilisateur u = new Utilisateur();
        u.setId(1L);
        u.setNom("Test");

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(u));

        Optional<Utilisateur> result = utilisateurService.getUtilisateurById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test", result.get().getNom());
    }

    @Test
    void testSaveUtilisateur() {
        Utilisateur u = new Utilisateur();
        u.setNom("New User");

        when(utilisateurRepository.save(u)).thenReturn(u);

        Utilisateur saved = utilisateurService.saveUtilisateur(u);

        assertNotNull(saved);
        assertEquals("New User", saved.getNom());
        verify(utilisateurRepository).save(u);
    }
}
